package com.orange.familyTree.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orange.familyTree.exceptions.AccountException;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;

@RestControllerAdvice(
		basePackages = { "com.springboot.familyTree.controller.*" },
		annotations = Controller.class)
public class ControllerAdvice {
	
	//查询异常
	@ExceptionHandler(value = MyCypherException.class)
	public Result cypherException(MyCypherException ex) {
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
	
}
