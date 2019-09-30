package com.orange.familyTree.service;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyFocusApplicationVO;
import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import com.orange.familyTree.pojo.specialPojo.LoginVO;
import com.orange.familyTree.pojo.specialPojo.RegisterVO;
import com.orange.familyTree.pojo.UserDO;

import java.util.ArrayList;

public interface UserService {

	// 查询用户昵称、电话号码、邮箱是否存在
	Boolean CheckUserInfoDuplicated(RegisterVO register) throws MySQLException;

	// 通过登录实体读取账号信息
	UserDO getUser(LoginVO loginVO) throws MyCypherException;

	// 通过用户ID读取账号信息
	UserDO getUserById(Long userId) throws MySQLException;

	// 通过用户nickname读取UserShow
	UserDO getUserByNickname(String userNickname) throws MySQLException;

	// 查询用户头像文件名
	String getUserAvatarFileName(String userNickname) throws MySQLException;
	
	// 注册账号
	void registerUser(RegisterVO registerVO) throws MyCypherException;

	// 修改密码
	void changePassword(Long userId, String oldPassword, String newPassword) throws MySQLException;

	// 修改用户头像文件名称
	void changeUserAvatarFileName(String userNickname, String newUserAvatarFileName) throws MySQLException;

	// 修改用户昵称和个人简介
	void changeUserNicknameAndIntroduction(String userNickname, String newUserNickname, String newUserIntroduction) throws MySQLException;

	// 获得用户关注的所有图谱的更新动态
	ArrayList<GenealogyUpdateRecordVO> getGenealogyUpdateRecord(Long userId) throws MySQLException;

	// 获得用户管理的所有图谱的关注请求
	ArrayList<GenealogyFocusApplicationVO> getGenealogyFocusApplicationByUserId(Long userId) throws MyCypherException;

	// 获得用户管理的所有图谱的请求数量
	Integer getAdminGenealogyFocusApplicationNum(Long userId) throws MySQLException;

	// 申请关注指定图谱
	void applyForGenealogy(String genealogyName, String userName, String applicationComment) throws MySQLException;
}
