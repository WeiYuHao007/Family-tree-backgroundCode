package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.List;

import com.orange.familyTree.entity.mysql.GenealogyMySQL;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyFocusApplicationVO;
import com.orange.familyTree.pojo.PersonVO;

public interface GenealogyService {

	// 查询图谱名称是否存在
	Boolean findWhetherHaveGenealogyName(String genealogyName) throws MySQLException;

	// 查询图谱默认中心节点名称
	String getGenealogyDefaultCenterPerson(String genealogyName) throws MySQLException;

	// 查询目标族谱的管理员
	List<Long> findGenealogyAdminsByName(String genealogyName) throws MyCypherException;
	
	// 查询目标族谱的所有关注者id
	List<Long> findGenealogyFollowersIdByName(String genealogyName) throws MyCypherException;

	// 查询目标族谱的所有关注者昵称
	List<String> findGenealogyFollowersName(String genealogyName) throws MyCypherException;

	// 查询用户关注的图谱
	List<String> findAllGenealogy(Long userId) throws MyCypherException;

	// 查询用户是否有关注的图谱
	Boolean findWhetherHaveFocusGenealogy(Long userId) throws MyCypherException;

	// 查询指定图谱拥有的所有节点名称
	List<String> findPersonsByGenealogyName (String genealogyName) throws MyCypherException;

	// 查询图谱详细信息
	GenealogyMySQL findGenealogyInfo(String genealogyName) throws MySQLException;

	// 关键词搜索图谱（显示公开信息）
	ArrayList<GenealogyMySQL> keywordSearch(String keyword, Integer pageNum) throws MySQLException,
			IllegalArgumentException;

	// 查看申请关注的用户名单
	ArrayList<GenealogyFocusApplicationVO> getApplicationList(String genealogyName) throws MySQLException;

	// 修改图谱名称
	Boolean changeGenealogyName(String genealogyName, String newName) throws MyCypherException, MySQLException;

	// 修改图谱描述
	Boolean changeGenealogyDescription(String genealogyName, String description) throws MyCypherException,
			MySQLException;

	// 设置默认中心节点
	Boolean setDefaultCenterPerson(String genealogyName, String personName) throws MySQLException;

	// 增设管理员
	Boolean setAdmin(String genealogyName, String newAdminNickname) throws MySQLException;

	// 转让管理员
	void transferAdmin(String genealogyName, String oldAdminNickname, String newAdminNickname) throws MySQLException;

	// 更改默认中心节点
	void changeDefaultCenterPerson(String genealogyName, String newCenterPerson) throws MySQLException;

	// 通过用户对图谱的关注申请
	void passApplication(String genealogyName, String userNickname) throws MySQLException;

	// 拒绝用户对图谱的关注申请
	void refuseApplication(String genealogyName, String userNickname) throws MySQLException;

	// 取消用户对图谱的关注
	void cancelGenealogyFocus(String genealogyName, String userNickname) throws MySQLException;

	// 创建新图谱
	void createNewGenealogy(Long userId, String newGenealogyName, String description, PersonVO person) throws MySQLException;
}
