package com.orange.familyTree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.*;
import com.orange.familyTree.pojo.specialPojo.LoginVO;
import com.orange.familyTree.pojo.specialPojo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class PublicUserController {

	@Autowired
	private UserService userService;

	// 用户登入
	@PostMapping(value="/user/status")
	public Result signIn(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody LoginVO loginVO) {
		UserDO userDO = userService.getUser(loginVO);
		//设置为登录状态
		HttpSession session = request.getSession(true);
		Long userId = userDO.getUserId();
		session.setAttribute("SESSION_USERID", userId);
		//会话存在时间为一小时
		session.setMaxInactiveInterval(3600);
		//将数据库映射对象转化为视图映射对象返回
		Result result = ResultFactory.buildSuccessResult("登陆成功。", userDO.getUserNickname());
		return result;
	}
	
	// 账户注册
	@PostMapping(value = "/user")
	public Result register(@RequestBody RegisterVO register) {
		userService.registerUser(register);
		return ResultFactory.buildSuccessResult("注册成功。");
	}

	// 获取登录账号的昵称
	@GetMapping(value= "/user/nickname")
	public Result getNickName(HttpServletRequest request) {
		try {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		UserDO userDO = userService.getUserById(userId);
		return ResultFactory.buildSuccessResult("请求成功", userDO.getUserNickname());
		}
		catch (MySQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@PatchMapping(value = "/user/password")
	public Result retrievePassword() {
		return null;
	}
}
