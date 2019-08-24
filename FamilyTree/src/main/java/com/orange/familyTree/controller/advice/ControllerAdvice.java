package com.orange.familyTree.controller.advice;

import com.orange.familyTree.exceptions.MySQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orange.familyTree.exceptions.UserException;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;

@RestControllerAdvice(
		basePackages = { "com.springboot.familyTree.controller.*" },
		annotations = Controller.class)
public class ControllerAdvice {
	
	// Neo4j查询异常
	@ExceptionHandler(value = MyCypherException.class)
	public Result cypherException(MyCypherException ex) {
		String message = ex.getMessage();
		Result result = ResultFactory.buildFailResult(message);
		return result;
	}

	// MySQL查询异常
	@ExceptionHandler(value = MySQLException.class)
	public Result sqlException(MySQLException ex) {
		String message = ex.getMessage();
		Result result = ResultFactory.buildFailResult(message);
		return result;
	}

	// 账户有关处理产生的异常
	@ExceptionHandler(value = UserException.class)
	public Result accountException(UserException ex) {
		String message = ex.getMessage();
		Result result = ResultFactory.buildFailResult(message);
		return result;
	}
	
}
