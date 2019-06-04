package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.dao.GenealogyCrudRepository;
import com.orange.familyTree.entity.Genealogy;

@RestController
public class GenealogyController {
	
	@Autowired
	private GenealogyCrudRepository genealogyCrudRepository;

	@GetMapping(value="/genealogy/{name}")
	public Genealogy findGenealogyByName(@PathVariable("name") String name) {
		return  genealogyCrudRepository.findByName(name);
	}
	

}
