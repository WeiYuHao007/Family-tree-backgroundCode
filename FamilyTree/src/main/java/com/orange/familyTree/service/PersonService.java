package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.pojo.GenealogyDetail;

public interface PersonService {
	
	//查看节点
	Person getPerson(String genealogyName, String personName);
	
	//查询两个指定节点间的最短路径
	List<Person> findShortPath(String genealogyName, String startPersonName, String endPersonName);

	//修改节点属性
	void modifyPersonProperties(GenealogyDetail newGenealogyDetail);
	
	//创建节点
	void createPerson(GenealogyDetail genealogyDetail);
	
	//创建节点关系
	void createPersonRelationship(String startPersonName, String endPersonName, String relationshipName);
	
	//删除节点关系
	void deletePersonRelationship(String startPersonName, String endPersonName, String relationshipName);
	
	//删除节点
	void deletePerson(String personName);

}
