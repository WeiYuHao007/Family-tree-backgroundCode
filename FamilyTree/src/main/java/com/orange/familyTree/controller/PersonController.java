package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.entity.Person;
import com.orange.familyTree.repository.GenealogyCrudRepository;
import com.orange.familyTree.repository.PersonCrudRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonCrudRepository personCrudRepository;
	@Autowired
	private GenealogyCrudRepository genealogyCrudRepository;
	
	@GetMapping(value="/person/{name}")
	@ResponseBody
	public Person findByname(@PathVariable("name") String name) {
		return personCrudRepository.findByName(name);
	}
	
	@RequestMapping(value="/genealogy/{name}")
	@ResponseBody
	public Genealogy findGenealogyByName(@PathVariable("name") String name) {
		return  genealogyCrudRepository.findByName(name);
	}

}
