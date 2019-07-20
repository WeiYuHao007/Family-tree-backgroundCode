package com.orange.familyTree.pojo.util;

public class ResultFactory {
	
	/*
	 * 工厂模式
	 * */
	
	//返回成功请求的结果(需要返回数据)
	public static Result buildSuccessResult(Object data) {
		return buildResult(ResultCode.SUCCESS, "请求成功", data);
	}
	
	//返回成功请求的结果(不需要返回数据)
	public static Result buildSuccessResult(String message) {
		return buildResult(ResultCode.SUCCESS, message, null);
	}
	
	//返回成功请求的结果(需要返回信息和数据)
	public static Result buildSuccessResult(String message, Object data) {
		return buildResult(ResultCode.SUCCESS, message, data);
	}
	
	//返回请求出现异常的信息的结果
	public static Result buildFailResult(String message) {
		return buildResult(ResultCode.FAIL, message, null);
	}
	
	//返回认证出现异常的结果
	public static Result buildAuthenticationResult(String message) {
		return buildResult(ResultCode.UNAUTHENTICATE, message, null);
	}
	
	
	//构造器（重载）
	public static Result buildResult(ResultCode resultCode, String message, Object data) {
		return buildResult(resultCode.code, message, data);
	}
	
	public static Result buildResult (int resultCode, String message, Object data) {
		return new Result(resultCode, message, data);
	}

}
