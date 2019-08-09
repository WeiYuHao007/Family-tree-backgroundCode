package com.orange.familyTree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.pojo.*;
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
	
	@PostMapping(value="/user/status")
	@ResponseBody
	public Result signIn(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody LoginVO loginVO) {
		//登入
		UserDO userDO = userService.findUser(loginVO);
		//设置为登录状态
		HttpSession session = request.getSession(true);
		Long userId = userDO.getUserId();
		session.setAttribute("SESSION_USERID", userId);
		//会话存在时间为一小时
		session.setMaxInactiveInterval(3600);
		//将数据库映射对象转化为视图映射对象返回
		UserVO userVO = UserDO.changeToVo(userDO);
		Result result = ResultFactory.buildSuccessResult("登陆成功。", userVO);
		return result;
	}
	
	
	@PostMapping(value = "/user")
	@ResponseBody
	public Result register(@RequestBody RegisterVO register) {
		//注册
		userService.registerUser(register);
		Result result = ResultFactory.buildSuccessResult("注册成功。");
		return result;
	}
}
