package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Person;

public interface PersonService {
	
	//Get
	//通过姓名获得节点
	Person getPerson(String name);
	
	//通过姓名查询两个指定人之间的关系路径
	List<Person> getPersonRelationshipPath(String startName, String endName);

	
	//Put
	//修改节点属性
	Person putPerson(Person modifiedPerson);
	
	
	//Post
	//创建节点
	void postPerson(Person person);
	
	//创建节点关系
	void postPersonRelationship(Person startPerson, String relationship, 
			Person endPerson);
	
	
	//Delete
	//删除节点关系
	void deletePersonRelationship(Person startPerson, String relationship,
			Person endPerson);
	
	//删除节点
	void deletePerson(Person person);

}
