package com.orange.familyTree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class ProtectedUserController {
	
	@Autowired
	private UserService userService;
	
	
	@DeleteMapping(value = "/user/status")
	@ResponseBody
	public Result logOut(HttpServletRequest request, HttpServletResponse response) {
		// 注销账号
		HttpSession session = request.getSession(false);
		session.invalidate();
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResultFactory.buildSuccessResult("注销成功。");
	}

}
