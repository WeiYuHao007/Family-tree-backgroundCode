package com.orange.familyTree.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.service.PersonService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/{nickName}/{genealogyName}")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	
	@GetMapping(value="/node")
	public Person findByname(@PathVariable("nickName") String nickName,
			@PathVariable("genealogyName") String genealogyName,
			@RequestParam("pn") String personName) {
		return null;
	}
	
	@GetMapping(value="/edge")
	public List<Person> getShortPath(HttpServletRequest request, @SessionAttribute("SESSION_NICKNAME") String nickName, 
			@PathVariable("genealogyName") String genealogyName, @RequestParam("spn") String startPersonName, 
			@RequestParam("epn") String endPersonName){
		return null;
	}

}
