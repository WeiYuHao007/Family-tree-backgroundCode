package com.orange.familyTree.pojo;

public class AccountViewDetail {
	
	private String email;
	
	private String telephoneNumber;
	
	private String password;
	
	
	public AccountViewDetail() {
		
	}
	
	public AccountViewDetail(String email, String telephoneNumber, String password) {
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
