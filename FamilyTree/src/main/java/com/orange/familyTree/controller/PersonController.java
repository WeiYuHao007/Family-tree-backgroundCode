package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.service.PersonService;

@RestController
@RequestMapping(value = "/api")
public class PersonController {
	
	@Autowired
	private PersonService personService;

}
