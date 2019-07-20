package com.orange.familyTree.pojo;


public class AccountVO {

	public AccountVO(String email, Integer telephoneNumber, String nickName, String registrationTime) {
		super();
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.nickName = nickName;
		this.registrationTime = registrationTime;
	}

	private String email;
	
	private Integer telephoneNumber;
	
	private String nickName;
	
	private String registrationTime;

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
}
