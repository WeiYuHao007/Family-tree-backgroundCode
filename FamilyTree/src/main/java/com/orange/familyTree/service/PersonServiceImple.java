package com.orange.familyTree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.repository.PersonCrudRepository;


@Service
public class PersonServiceImple implements PersonService{
	
	@Autowired
	private PersonCrudRepository personCrudRepository;

	@Override
	public Person getPerson(String name) {
		return personCrudRepository.findByName(name);
	}

	@Override
	public Person modifyPersonProperties(Person person, String property) {
		//
		return null;
	}

	@Override
	public void createPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPersonRelationship(Person startPerson, String relationship, Person endPerson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonRelationship(Person startPerson, String relationship, Person endPerson) {
		// TODO Auto-generated method stub
		
	}

}
