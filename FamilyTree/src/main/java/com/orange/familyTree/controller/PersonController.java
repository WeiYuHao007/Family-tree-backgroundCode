package com.orange.familyTree.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orange.familyTree.entity.Person;
import com.orange.familyTree.repository.RetrieveRepository;

@Controller
public class PersonController {
	
	@Autowired
	private RetrieveRepository retrieveRepository;
	
	@RequestMapping(value="/person/{name}")
	@ResponseBody
	public List<Person> findByname(@PathVariable("name") String name) {
		return retrieveRepository.findCloseRelativeByName(name);
	}

}
