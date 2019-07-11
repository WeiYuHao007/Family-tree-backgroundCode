package com.orange.familyTree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.AccountDetail;
import com.orange.familyTree.pojo.AccountViewDetail;
import com.orange.familyTree.pojo.tools.Result;
import com.orange.familyTree.pojo.tools.ResultFactory;
import com.orange.familyTree.service.AccountService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class IndexController {

	@Autowired
	private AccountService accountService;
	
	//登入
	@PostMapping(value="/account/signIn")
	@ResponseBody
	public Result signIn(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody AccountViewDetail accountViewDetail) {
		AccountDetail accountDetail = accountService.findAccount(accountViewDetail);
		//设置为登录状态
		HttpSession session = request.getSession(true);
		String nickName = accountDetail.getNickName();
		session.setAttribute("SESSION_NICKNAME", nickName);
		//会话存在时间为一小时
		session.setMaxInactiveInterval(3600);
		
		//由于Cookie的跨域，暂时在开发时使用
		System.out.println(session.getId());
		
		//将数据库映射对象转化为视图映射对象返回
		Result result = ResultFactory.buildSuccessResult("登陆成功。");
		return result;
	}
	
	@PostMapping(value = "/account/register")
	@ResponseBody
	public Result register(@RequestBody AccountDetail accountDetail) {
		Result result = ResultFactory.buildSuccessResult("注册成功。");
		return result;
	}
}
