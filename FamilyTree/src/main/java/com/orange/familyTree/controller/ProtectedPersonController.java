package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.specialPojo.RelationshipVO;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.PersonService;

@RestController
@RequestMapping(value = "/api")
public class ProtectedPersonController {
	
	@Autowired
	private PersonService personService;

	// 增加节点
	@PostMapping(value = "/tree/{tree-name}/node")
	public Result addPerson(@PathVariable("tree-name") String genealogyName, 
			@RequestBody PersonVO personVO) {
		return personService.createPerson(genealogyName, personVO);
	}
	
	// 增加关系
	@PostMapping(value = "/tree/{tree-name}/relationship")
	public Result addRelationship(@PathVariable("tree-name") String genealogyName, 
			@RequestBody RelationshipVO relationshipVO) {
		return personService.createRelationship(genealogyName, relationshipVO);
	}

	// 修改节点信息
	@PutMapping(value = "/tree/{tree-name}/node-info")
	public Result changePersonInfo(@PathVariable("tree-name") String genealogyName, @RequestBody PersonVO personVO) {
		return personService.changePersonInfo(genealogyName, personVO);
	}

	// 删除节点
	@DeleteMapping(value = "/tree/{tree-name}/node/{nodeName}")
	public Result deletePerson(@PathVariable("tree-name") String genealogyName,
							   @PathVariable("nodeName") String personName) {
		return personService.deletePerson(genealogyName, personName);
	}

	// 删除关系
	@DeleteMapping(value = "/tree/{tree-name}/relationship")
	public Result deleteRelationship(@PathVariable("tree-name") String genealogyName, 
			@RequestParam("source") String sourceName, @RequestParam("target") String targetName) {
		return personService.deleteRelationship(genealogyName, sourceName, targetName);
	}
}
