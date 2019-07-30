package com.orange.familyTree.pojo;

import java.util.List;

import com.orange.familyTree.entity.Account;

public class AccountDO {
	
	// 从数据库中读出的Account,数据具有敏感性
	
	private String email;
	
	private Integer telephoneNumber;
	
	private String password;
	
	private String nickName;
	
	private List<String> privilegeRole;
	
	private String registrationTime;
	
	public AccountDO() {
		
	}
	
	public AccountDO(String email, Integer telephoneNumber, String password, 
			String nickName, List<String> privilegeRole, String registrationTime) {
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
		this.nickName = nickName;
		this.privilegeRole = privilegeRole;
		this.registrationTime = registrationTime;
	}
	
	//数据库映射对象转化为前端英映射对象
	public static AccountDO changeAToDO(Account account) {
		
		String email = account.getEmail();
		Integer telephoneNumber = account.getTelephoneNumber();
		String password = account.getPassword();
		String nickName = account.getNickName();
		List<String> privilegeRole = account.getPrivilegeRole();
		String registrationTime = account.getRegistrationTime();
		AccountDO accountDetail = new AccountDO(email, 
				telephoneNumber, password, nickName, privilegeRole, registrationTime);
		return accountDetail;
		
	}
	
	public static AccountVO changeToVO(AccountDO accountDO) {
		
		String email = accountDO.getEmail();
		Integer telephoneNumber = accountDO.getTelephoneNumber();
		String nickName = accountDO.getNickName();
		String registrationTime = accountDO.getRegistrationTime();
		AccountVO accountVO = new AccountVO(email, telephoneNumber, nickName, registrationTime);
		return accountVO;
		
	}

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
