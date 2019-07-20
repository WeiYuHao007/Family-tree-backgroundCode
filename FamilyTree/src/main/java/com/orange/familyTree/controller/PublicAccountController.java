package com.orange.familyTree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.AccountDO;
import com.orange.familyTree.pojo.AccountVO;
import com.orange.familyTree.pojo.LoginVO;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.AccountService;

@RestController
@RequestMapping(value = "/api")
public class PublicAccountController {

	@Autowired
	private AccountService accountService;
	
	
	@PostMapping(value="/account/signIn")
	@ResponseBody
	public Result signIn(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody LoginVO loginVo) {
		//登入
		AccountDO accountDO = accountService.findAccount(loginVo);
		//设置为登录状态
		HttpSession session = request.getSession(true);
		Integer telephoneNumber = accountDO.getTelephoneNumber();
		session.setAttribute("SESSION_TELEPHONENUMBER", telephoneNumber);
		//会话存在时间为一小时
		session.setMaxInactiveInterval(3600);
		//将数据库映射对象转化为视图映射对象返回
		AccountVO accountVO = AccountDO.changeToVO(accountDO);
		Result result = ResultFactory.buildSuccessResult("登陆成功。", accountVO);
		return result;
	}
	
	
	@PostMapping(value = "/account/register")
	@ResponseBody
	public Result register(@RequestBody AccountDO accountDetail) {
		//注册
		Result result = ResultFactory.buildSuccessResult("注册成功。");
		return result;
	}
}
