package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.familyTree.dao.PersonCrudRepository;
import com.orange.familyTree.entity.Person;


@Service
public class PersonServiceImple implements PersonService{
	
	@Autowired
	private PersonCrudRepository personCrudRepository;

	
	//Get
	@Override
	public Person getPerson(String name) {
		/*通过名字获取节点：*/
		Person myPerson = personCrudRepository.findByName(name);
		return myPerson;
	}

	@Override
	public List<Person> gerPeToPeShortPath(String startPerson, String endPerson) {
		int pathLength = personCrudRepository.getShortPathLength(startPerson, endPerson);
		List<Person> myListPerson = new ArrayList<Person>();
		for(int i = 0; i <= pathLength; i++) {
			myListPerson.add(personCrudRepository.findShortPath(startPerson, endPerson, i));
		}
		return myListPerson;
	}



	//Delete
	@Override
	public void deletePerson(Person person) {
		/*删除节点：
		 * 1.找人。
		 * 2.没有的话报错。
		 * 3.有的话删除*/
		Person MyPerson = personCrudRepository.findByName(person.getName());
		personCrudRepository.deleteByName(MyPerson.getName());
	}

	@Override
	public void deletePersonRelationship(Person startPerson, String relationship, Person endPerson) {
		/*删除关系：
		 * 1.找起始人。
		 * 2.找断终人。
		 * 3.找关系。
		 * 4.删除关系*/
		Person MyStartPerson = personCrudRepository.findByName(startPerson.getName());
		
	}
	
	
	//Post
	@Override
	public void createPerson(Person person) {
		/* 创建节点：
		 * */
	}
	
	@Override
	public void createPersonRelationship(String startPerson, String relationship, String endPerson) {
		/*创建关系:
		 * 1.找起始人。
		 * 2.找断终人。
		 * 3.创建关系。*/
		Person myStartPerson = personCrudRepository.findByName(startPerson);
		Person myEndPerson = personCrudRepository.findByName(endPerson);
		
	}


	//Put
	@Override
	public Person modifyPerson(Person modifiedPerson) {
		/*修改节点属性：
		 * 1.找到被修改的人。
		 * 2.进行修改
		 * 4.返回被修改后的人
		 * */
		return null;
	}
}
