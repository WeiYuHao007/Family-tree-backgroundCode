package com.orange.familyTree.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;

@RestController
@RequestMapping(value = "/api")
public class PublicGenealogyController {
	
	@Autowired
	private GenealogyService genealogyService;

	// 获得登录用户关注的所有图谱名称
	@GetMapping(value="/trees")
	public Result findGenealogyByName(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		List<String> nameList = genealogyService.findAllGenealogy(userId);
		return ResultFactory.buildSuccessResult(nameList);
	}

	// 获得图谱的所有关注者
	@GetMapping(value="/tree/{tree-name}/followers")
	public Result findFollowersByGenealogyName(@PathVariable("tree-name") String genealogyName) {
		return null;
	}
	

}
