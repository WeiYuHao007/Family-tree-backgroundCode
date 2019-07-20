package com.orange.familyTree.pojo;

public class RegisterVO {
	
	public RegisterVO() {
		
	}
	
	public RegisterVO(String email, Integer telephoneNumber, String password, String nickName) {
		super();
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
		this.nickName = nickName;
	}

	private String email;
	
	private Integer telephoneNumber;
	
	private String password;
	
	private String nickName;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
