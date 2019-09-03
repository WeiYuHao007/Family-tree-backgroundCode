package com.orange.familyTree.controller;

import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class PublicPersonController {
	
	@Autowired
	private PersonService personService;

	@Autowired
	private GenealogyService genealogyService;

	// 获得指定图谱拥有的所有节点名称
	@GetMapping(value = "/tree/{tree-name}/nodes")
	public Result getPersonsByGenealogyName(@PathVariable("tree-name") String genealogyName) {
		List<String> personNameList = genealogyService.findPersonsByGenealogyName(genealogyName);
		return ResultFactory.buildSuccessResult(personNameList);
	}

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

	// 获得图谱主要渲染数据
	@GetMapping(value = "/tree/{tree-name}/tree-main-data")
	public Result getGenealogyMainData(
			@PathVariable("tree-name") String genealogyName,
			@RequestParam("center_node") String centerPersonName, @RequestParam("radius") Integer radius){
		return personService.getGenealogyMainData(genealogyName, centerPersonName, radius);
	}

	// 获得图谱指定节点信息
	@GetMapping(value="/tree/{tree-name}/node")
	public Result getPersonInfo(
			@PathVariable("tree-name") String genealogyName, @RequestParam("name") String personName) {
		Person person = personService.getPerson(genealogyName, personName);
		PersonVO personVO = Person.changeToVO(person);
		return ResultFactory.buildSuccessResult(personVO);
	}
}
