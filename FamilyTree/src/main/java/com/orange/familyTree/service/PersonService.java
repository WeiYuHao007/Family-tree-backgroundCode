package com.orange.familyTree.service;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.pojo.GenealogyDO;
import com.orange.familyTree.pojo.NodeVO;
import com.orange.familyTree.pojo.util.Result;

public interface PersonService {
	
	// 查看指定节点的妻子与女儿
	Result getWifesAndDaughters(String genealogyName, NodeVO nodeVO, Integer radius);
	
	// 查看指定节点的儿子
	Result getSons(String genealogyName, NodeVO nodeVO, Integer radius);
	
	// 查看节点
	Person getPerson(String genealogyName, String personName);
	
	// 查询两个指定节点间的最短路径
	Result findShortPath(String genealogyName, String startPersonName, String endPersonName, 
			Integer radius);

	// 修改节点属性
	void modifyPersonProperties(GenealogyDO newGenealogyDetail);
	
	// 创建节点
	void createPerson(GenealogyDO genealogyDetail);
	
	// 创建节点关系
	void createPersonRelationship(String startPersonName, String endPersonName, String relationshipName);
	
	// 删除节点关系
	void deletePersonRelationship(String startPersonName, String endPersonName, String relationshipName);
	
	// 删除节点
	void deletePerson(String personName);

}
