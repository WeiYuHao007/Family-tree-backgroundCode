package com.orange.familyTree.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping(value="/account/genealogy/findAll")
	public Result findGenealogyByName(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Integer phoneNum = (Integer)session.getAttribute("SESSION_TELEPHONENUMBER");
		List<String> nameList = genealogyService.findAllGenealogy(phoneNum);
		Result result = ResultFactory.buildSuccessResult(nameList);
		return result;
	}
	

}
