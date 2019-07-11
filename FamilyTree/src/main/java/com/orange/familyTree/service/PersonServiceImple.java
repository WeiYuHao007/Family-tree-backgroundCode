package com.orange.familyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.pojo.GenealogyDetail;
import com.orange.familyTree.repository.PersonCrudRepository;


@Service
@Transactional
public class PersonServiceImple implements PersonService{
	
	@Autowired
	private PersonCrudRepository personCrudRepository;


	@Override
	public Person getPerson(String genealogyName, String personName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findShortPath(String genealogyName, String startPersonName, String endPersonName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyPersonProperties(GenealogyDetail newGenealogyDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPerson(GenealogyDetail genealogyDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPersonRelationship(String startPersonName, String endPersonName, String relationshipName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonRelationship(String startPersonName, String endPersonName, String relationshipName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(String personName) {
		// TODO Auto-generated method stub
		
	}

}
