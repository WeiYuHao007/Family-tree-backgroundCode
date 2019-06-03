package com.orange.familyTree.service;

import com.orange.familyTree.entity.Person;

public interface PersonService {
	
	//获得节点
	Person getPerson(String name);
	
	//修改节点属性
	Person modifyPersonProperties(Person person, String property);
	
	//创建节点
	void createPerson(Person person);
	
	//删除节点
	void deletePerson(Person person);
	
	//创建节点关系
	void createPersonRelationship(Person startPerson, String relationship, 
			Person endPerson);
	
	//删除节点关系
	void deletePersonRelationship(Person startPerson, String relationship,
			Person endPerson);

}
