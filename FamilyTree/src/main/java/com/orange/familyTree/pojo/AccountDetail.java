package com.orange.familyTree.pojo;

import com.orange.familyTree.entity.Account;

public class AccountDetail {
	
	//前端账户映射对象
	public AccountDetail(String nickName, String email, String telephoneNumber, String password) {
		super();
		this.nickName = nickName;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
	}
	
	//数据库映射对象转化为前端英映射对象
	public static AccountDetail changeToAccountDetail(Account account) {
		String nickName = account.getNickName();
		String email = account.getEmail();
		String telephoneNumber = account.getTelephoneNumber();
		String password = account.getPassword();
		AccountDetail accountDetail = new AccountDetail(nickName, email, telephoneNumber,
				password);
		return accountDetail;
	}

	private String nickName;
	
	private String email;
	
	private String telephoneNumber;
	
	private String password;

	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
