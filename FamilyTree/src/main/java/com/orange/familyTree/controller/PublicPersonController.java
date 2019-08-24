package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 返回两个指定节点的最短路径
	// 返回格式[[nodeNames],[relationshipNames]]
	@GetMapping(value = "/tree/{tree-name}/node/shortestpath")
	public Result findShortestPathNode(
			@PathVariable("tree-name") String genealogyName, 
			@RequestParam("startPersonName") String startPersonName,
			@RequestParam("endPersonName") String endPersonName,
			@RequestParam("radius") Integer radius) {
		return personService.getShortPath(genealogyName, startPersonName, endPersonName, radius);
	}

	// 获得指定节点的所有妻子与女儿节点
	@GetMapping(value = "/tree/{tree-name}/node/{person-name}/wives-and-daughters")
	public Result findWivesAndDaughters(
			@PathVariable("tree-name") String genealogyName, @RequestParam("name") String personName,
			@RequestParam("x") Integer x, @RequestParam("y") Integer y, 
			@RequestParam("radius") Integer radius) {
		return personService.getWivesAndDaughters(genealogyName, personName, x, y, radius);
	}


	// 获得指定节点的所有儿子节点
	@GetMapping(value = "/tree/{tree-name}/node/{person-name}/sons")
	public Result findSons(
			@PathVariable("tree-name") String genealogyName, @RequestParam("name") String personName,
			@RequestParam("x") Integer x, @RequestParam("y") Integer y, 
			@RequestParam("radius") Integer radius) {
		return personService.getSons(genealogyName, personName, x, y, radius);
	}
}
