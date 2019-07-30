package com.orange.familyTree.pojo;

public class LoginVO {
	
	// 账户登录时前端提交的Account
	
	public LoginVO() {
		
	}
	
	public LoginVO(String email, Integer telephoneNumber, String password) {
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
	}
	
	private String email;
	
	private Integer telephoneNumber;
	
	private String password;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Integer telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
