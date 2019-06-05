package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Person;

public interface PersonService {
	
	//Get
	//通过姓名获得节点
	Person getPerson(String name);
	
	//查询两者间的关系
	List<Person> gerPeToPeShortPath(String startPerson,String endPerson);

	
	//Put
	//修改节点属性
	Person modifyPerson(Person modifiedPerson);
	
	//修改人物间的关系
	
	
	//Post
	//创建节点
	void createPerson(Person person);
	
	//创建节点关系
	void createPersonRelationship(String startPerson, String relationship, 
			String endPerson);
	
	
	//Delete
	//删除节点关系
	void deletePersonRelationship(Person startPerson, String relationship,
			Person endPerson);
	
	//删除节点
	void deletePerson(Person person);

}
