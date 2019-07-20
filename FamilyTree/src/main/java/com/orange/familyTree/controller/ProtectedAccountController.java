package com.orange.familyTree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.AccountService;

@RestController
@RequestMapping(value = "/api")
public class ProtectedAccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping(value = "/account/logOut")
	@ResponseBody
	public Result logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
			Cookie cookie = new Cookie("JSESSIONID",session.getId());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			return ResultFactory.buildSuccessResult("注销成功。");
		}
		else {
			return ResultFactory.buildAuthenticationResult("无有效身份证明,无需注销。");
		}
	}

}
