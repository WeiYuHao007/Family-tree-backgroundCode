package com.orange.familyTree.service;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.AccountDO;
import com.orange.familyTree.pojo.LoginVO;

public interface AccountService {
	
	//查询账号
	AccountDO findAccount(LoginVO loginVo) throws MyCypherException;
	
	//注册账号
	void registerAccount(AccountDO accountDetail) throws MyCypherException;

	//修改密码
	void changePassword(LoginVO accountViewDetail) throws MyCypherException;

}
