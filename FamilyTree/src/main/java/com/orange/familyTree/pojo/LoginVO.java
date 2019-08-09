package com.orange.familyTree.pojo;

public class LoginVO {
	
	// 账户登录时前端提交的User
	
	public LoginVO() { }
	
	public LoginVO(String email, Integer phoneNum, String password) {
		this.email = email;
		this.phoneNum = phoneNum;
		this.password = password;
	}
	
	private String email;
	
	private Integer phoneNum;
	
	private String password;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
