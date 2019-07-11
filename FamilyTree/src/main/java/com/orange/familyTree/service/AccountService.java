package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.exceptions.CypherException;
import com.orange.familyTree.pojo.AccountDetail;
import com.orange.familyTree.pojo.AccountViewDetail;

public interface AccountService {
	
	//查询账号
	AccountDetail findAccount(AccountViewDetail accountViewDetail) throws CypherException;
	
	//查看指定账号关注的全部族谱
	List<Genealogy> viewAllFocusOnGenealogy(String accountName) throws CypherException;
	
	//注册账号
	void registerAccount(AccountDetail accountDetail) throws CypherException;
	
	//关注指定图谱
	void focusOnGenealogy(String accountName, String genealogyName) throws CypherException;
	
	//取消对指定图谱的关注
	void cancelFocusOnGenealogy(String accountName, String genealogyName) throws CypherException;
	
	//修改密码
	void changePassword(String accountName, String password) throws CypherException;

	void changePassword(AccountViewDetail accountViewDetail) throws CypherException;

}
