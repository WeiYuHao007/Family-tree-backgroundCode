package com.orange.familyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.repository.PersonCrudRepository;


@Service
public class PersonServiceImple implements PersonService{
	
	@Autowired
	private PersonCrudRepository personCrudRepository;

	
	//Get
	@Override
	@Transactional
	//获取单个节点信息
	public Person getPerson(String nickName, String genealogyName, String personName) {
		Person myPerson = personCrudRepository.findByName(nickName, genealogyName, personName);
		return myPerson;
	}

	@Override
	@Transactional
	//获取两个指定节点间的最短路径
	public List<Person> findShortPath(String nickName, String genealogyName, String startPersonName, 
			String endPersonName) {
		return personCrudRepository.findShortPath(nickName, genealogyName, startPersonName,
				endPersonName);
	}


	//Delete
	@Override
	public void deletePerson() {

	}

	@Override
	public void deletePersonRelationship() {
		
	}
	
	
	//Post
	@Override
	public void createPerson() {
	}
	
	@Override
	public void createPersonRelationship() {
	}


	//Put
	@Override
	public Person modifyPersonProperties() {
		return null;
	}

}
