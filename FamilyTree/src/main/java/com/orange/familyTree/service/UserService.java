package com.orange.familyTree.service;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import com.orange.familyTree.pojo.specialPojo.LoginVO;
import com.orange.familyTree.pojo.specialPojo.RegisterVO;
import com.orange.familyTree.pojo.UserDO;

import java.util.ArrayList;

public interface UserService {
	
	// 通过登录实体读取账号信息
	UserDO getUser(LoginVO loginVO) throws MyCypherException;

	// 通过用户ID读取账号信息
	UserDO getUserById(Long userId) throws MySQLException;

	// 通过用户nickname读取UserShow
	UserDO getUserByNickname(String userNickname) throws MySQLException;
	
	// 注册账号
	void registerUser(RegisterVO registerVO) throws MyCypherException;

	// 修改密码
	void changePassword(Long userId, String oldPassword, String newPassword) throws MySQLException;

	// 获得用户关注的所有图谱的更新动态
	ArrayList<GenealogyUpdateRecordVO> getGenealogyUpdateRecord(Long userId) throws MySQLException;

	// 申请关注指定图谱
	void applyForGenealogy(String genealogyName, String userName, String applicationComment) throws MySQLException;
}
