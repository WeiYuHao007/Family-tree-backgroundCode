package com.orange.familyTree.service;

import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.specialPojo.RelationshipVO;
import com.orange.familyTree.pojo.util.Result;

public interface PersonService {

	// 查询圆形展开的主要节点数据
	Result getCircleMainPersonData(String genealogyName, String centerPersonName, Integer radius) throws MyCypherException;

	// 查询散列的主要节点数据
	Result getHashMainPersonData(String genealogyName, Integer groupNum);

	// 查询节点
	Person getPerson(String genealogyName, String personName);

	// 查询节点是否存在
	Boolean findPersonWhetherExist(String genealogyName, String personName);

	// 查询两个指定节点间的最短路径
	Result getShortPath(String genealogyName, String startPersonName, String endPersonName, 
			Integer radius);
	
	// 创建节点
	Result createPerson(String genealogyName, PersonVO personVO, String adminNickname);
	
	// 创建节点关系
	Result createRelationship(String genealogyName, RelationshipVO relationshipVO, String adminNickname);

	// 修改节点信息
	Result changePersonInfo(String genealogyName, PersonVO personVO, String adminNickname) throws MyCypherException;

	// 删除节点
	Result deletePerson(String genealogyName, String personName, String adminNickname);
	
	// 删除节点关系
	Result deleteRelationship(String genealogyName, String sourceName, String targetName, String adminNickname);

}
