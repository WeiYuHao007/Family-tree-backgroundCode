package com.orange.familyTree.service;

import com.orange.familyTree.exceptions.CypherException;
import com.orange.familyTree.pojo.AccountDO;
import com.orange.familyTree.pojo.LoginVO;

public interface AccountService {
	
	//查询账号
	AccountDO findAccount(LoginVO loginVo) throws CypherException;
	
	//注册账号
	void registerAccount(AccountDO accountDetail) throws CypherException;

	//修改密码
	void changePassword(LoginVO accountViewDetail) throws CypherException;

}
