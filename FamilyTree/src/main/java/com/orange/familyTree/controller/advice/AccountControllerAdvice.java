package com.orange.familyTree.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orange.familyTree.exceptions.AccountException;
import com.orange.familyTree.exceptions.CypherException;
import com.orange.familyTree.pojo.tools.Result;
import com.orange.familyTree.pojo.tools.ResultFactory;

@RestControllerAdvice(
		basePackages = { "com.springboot.familyTree.controller.*" },
		annotations = Controller.class)
public class AccountControllerAdvice {
	
	//查询异常
	@ExceptionHandler(value = CypherException.class)
	public Result cypherException(CypherException ex) {
		String message = ex.getMessage();
		Result result = ResultFactory.buildFailResult(message);
		return result;
	}
	
	//账户有关处理产生的异常
	@ExceptionHandler(value = AccountException.class)
	public Result accountException(AccountException ex) {
		String message = ex.getMessage();
		Result result = ResultFactory.buildFailResult(message);
		return result;
	}
	
	//账户未登入或者登入信息失效的异常
	@ExceptionHandler(value = ServletRequestBindingException.class)
	public Result authenticationException(Exception ex) {
		String message = "无身份证明或身份证明过期，请重新登陆以获得新的证明";
		Result result = ResultFactory.buildAuthenticationResult(message);
		return result;
	}
	
}
