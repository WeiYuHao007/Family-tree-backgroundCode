package com.orange.familyTree.pojo.util;

public enum ResultCode {
	
	//成功
	SUCCESS(200),
	
	//失败
	FAIL(400),
	
	//身份验证出现错误
	UNAUTHENTICATE(401),
	
	//接口不存在
	NOT_FOUND(404),
	
	//服务器内部错误
	INTERNAL_SERVER_ERROR(500);
	
	
	public int code;
	
	
	ResultCode(int code){
		this.code = code;
	}
}
