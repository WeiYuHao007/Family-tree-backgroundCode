package com.orange.familyTree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	@GetMapping(value="/{nickName}/{genealogyName}/{personName}")
	public Person findByname(@PathVariable("nickName") String nickName,
			@PathVariable("genealogyName") String genealogyName,
			@PathVariable("personName") String name) {
		return personService.getPerson(name);
	}
	
	@GetMapping(value="/{nickName}/{genealogyName}/{startPerson}/{endPerson}")
	public List<Person> getPersonRelationshipPath(@PathVariable("startPerson") String startPerson,
			@PathVariable("endPerson") String endPerson){
		return personService.gerPeToPeShortPath(startPerson, endPerson);
		
	}


}
