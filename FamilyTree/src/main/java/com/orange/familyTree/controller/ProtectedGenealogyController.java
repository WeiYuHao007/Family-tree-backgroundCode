package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.GenealogyService;

@RestController
@RequestMapping(value = "/api")
public class ProtectedGenealogyController {

	@Autowired
	private GenealogyService genealogyService;
	
	@PatchMapping(value="/tree/{tree-name}/name")
	public Result changeGenealogyName() {
		return null;
	}
}
