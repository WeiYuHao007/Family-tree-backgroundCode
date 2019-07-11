package com.orange.familyTree.pojo;

import java.util.List;

import com.orange.familyTree.entity.Account;

public class AccountDetail {
	
	private String email;
	
	private String telephoneNumber;
	
	private String password;
	
	private String nickName;
	
	private List<String> privilegeRole;
	
	private String registrationTime;
	
	
	//构造器
	public AccountDetail() {
		
	}
	
	public AccountDetail(String email, String telephoneNumber, String password, 
			String nickName, List<String> privilegeRole, String registrationTime) {
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
		this.nickName = nickName;
		this.privilegeRole = privilegeRole;
		this.registrationTime = registrationTime;
	}
	
	
	//数据库映射对象转化为前端英映射对象
	public static AccountDetail changeAToAD(Account account) {
		
		String email = account.getEmail();
		String telephoneNumber = account.getTelephoneNumber();
		String password = account.getPassword();
		String nickName = account.getNickName();
		List<String> privilegeRole = account.getPrivilegeRole();
		String registrationTime = account.getRegistrationTime();
		AccountDetail accountDetail = new AccountDetail(email, 
				telephoneNumber, password, nickName, privilegeRole, registrationTime);
		return accountDetail;
		
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getPrivilegeRole() {
		return privilegeRole;
	}

	public void setPrivilegeRole(List<String> privilegeRole) {
		this.privilegeRole = privilegeRole;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	
}
