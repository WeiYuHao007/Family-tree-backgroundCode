package com.orange.familyTree.service;

import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.specialPojo.RelationshipVO;
import com.orange.familyTree.pojo.util.Result;

public interface PersonService {
	
	// 查看指定节点的妻子与女儿
	Result getWivesAndDaughters(String genealogyName, String personName, Integer x, Integer y, 
			Integer radius);
	
	// 查看指定节点的儿子
	Result getSons(String genealogyName, String personName, Integer x,Integer y, Integer radius);
	
	// 查看节点
	Person getPerson(String genealogyName, String personName);
	
	// 查询两个指定节点间的最短路径
	Result getShortPath(String genealogyName, String startPersonName, String endPersonName, 
			Integer radius);
	
	// 创建节点
	Result createPerson(String genealogyName, PersonVO personVO);
	
	// 创建节点关系
	Result createRelationship(String genealogyName, RelationshipVO relationshipVO);
	
	// 删除节点
	Result deletePerson(String genealogyName, String personName);
	
	// 删除节点关系
	Result deleteRelationship(String genealogyName, String sourceName, String targetName);

}
