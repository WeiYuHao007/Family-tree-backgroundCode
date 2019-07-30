package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.PersonService;

@RestController
@RequestMapping(value = "/api")
public class PublicPersonController {
	
	@Autowired
	private PersonService personService;

	@GetMapping(value = "/tree/{treeName}/shortestpath")
	public Result findShortestPathNode(
			@RequestParam("treeName") String genealogyName,
			@RequestParam("startPersonName") String startPersonName,
			@RequestParam("endPersonName") String endPersonName) {
		return personService.findShortPath(genealogyName, startPersonName, endPersonName);
	}
}
