package com.orange.familyTree.service;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.LoginVO;
import com.orange.familyTree.pojo.RegisterVO;
import com.orange.familyTree.pojo.UserDO;

public interface UserService {
	
	//查询账号
	UserDO findUser(LoginVO loginVo) throws MyCypherException;
	
	//注册账号
	void registerUser(RegisterVO registerVO) throws MyCypherException;

	//修改密码
	void changePassword(LoginVO accountViewDetail) throws MyCypherException;

}
