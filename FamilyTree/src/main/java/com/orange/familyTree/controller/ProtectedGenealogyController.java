package com.orange.familyTree.controller;

import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.util.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.GenealogyService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
public class ProtectedGenealogyController {

	// 权限条件：关注图谱

	@Autowired
	private GenealogyService genealogyService;


	// 获得图谱的所有关注者
	@GetMapping(value = "/tree/{tree-name}/followers")
	public Result findFollowersByGenealogyName(@PathVariable("tree-name") String genealogyName) {
		return ResultFactory.buildSuccessResult(genealogyService.findGenealogyOrdinaryFollowers(genealogyName));
	}

	// 获得图谱的详细信息
	@GetMapping(value = "/tree/{tree-name}")
	public Result findGenealogyDetailedInfo(@PathVariable("tree-name") String genealogyName) throws MySQLException {
		return ResultFactory.buildSuccessResult(genealogyService.findGenealogyInfo(genealogyName));
	}
}
