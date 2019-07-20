package com.orange.familyTree.pojo.util;

public class Result {
	
	//响应状态码
	public int code;
	
	//响应提示信息
	private String message;
	
	//响应结果对象
	private Object data;
	
	
	public Result(int code, String message, Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
