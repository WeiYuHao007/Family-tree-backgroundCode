package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Person;

public interface PersonService {
	
	//Get
	//通过姓名获得节点
	Person getPerson(String nickName, String genealogyName, String personName);
	
	//查询两个指定节点间的最短路径
	List<Person> findShortPath(String nickName, String genealogyName, String startPersonName, 
			String endPersonName);

	
	//Put
	//修改节点属性
	Person modifyPersonProperties();
	
	//Post
	//创建节点
	void createPerson();
	
	//创建节点关系
	void createPersonRelationship();
	
	
	//Delete
	//删除节点关系
	void deletePersonRelationship();
	
	//删除节点
	void deletePerson();

}
