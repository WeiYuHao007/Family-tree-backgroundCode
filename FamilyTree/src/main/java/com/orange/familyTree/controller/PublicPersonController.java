package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.NodeVO;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.PersonService;

@RestController
@RequestMapping(value = "/api")
public class PublicPersonController {
	
	@Autowired
	private PersonService personService;

	@GetMapping(value = "/tree/{treeName}/shortestpath")
	public Result findShortestPathNode(
			@PathVariable("treeName") String genealogyName,
			@RequestParam("startPersonName") String startPersonName,
			@RequestParam("endPersonName") String endPersonName,
			@RequestParam("radius") Integer radius) {
		// 返回两个指定节点的最短路径
		// 返回格式[[nodeNames],[relationshipNames]]
		return personService.findShortPath(genealogyName, startPersonName, endPersonName, radius);
	}
	
	@PostMapping(value = "/tree/{tree_name}/{person_name}/wives-and-daughters/{radius}")
	public Result findWivesAndDaughters(
			@PathVariable("tree_name") String genealogyName,
			@PathVariable("radius") Integer radius, @RequestBody NodeVO nodeVO) {
		return personService.getWivesAndDaughters(genealogyName, nodeVO, radius);
	}
	
	@PostMapping(value = "/tree/{treeName}/{personName}/sons/{radius}")
	public Result findSons(
			@PathVariable("treeName") String genealogyName,
			@RequestBody NodeVO nodeVO, @PathVariable("radius") Integer radius) {
		return personService.getSons(genealogyName, nodeVO, radius);
	}
}
