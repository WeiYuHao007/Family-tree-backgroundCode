package com.orange.familyTree.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.entity.mysql.GenealogyMySQL;
import com.orange.familyTree.exceptions.MySQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.GenealogyService;

@RestController
@RequestMapping(value = "/api")
public class PublicGenealogyController {

	// 权限条件：无

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
	
	// 关键词搜索图谱（显示图谱的不敏感信息）
	@GetMapping(value = "/trees/info")
	public Result keywordSearch(HttpServletRequest request, @RequestParam("keyword") String keyword,
								@RequestParam("pageNum") Integer pageNum) throws MySQLException {
		ArrayList<GenealogyMySQL> genealogiesList= genealogyService.keywordSearch(keyword, pageNum);
		return ResultFactory.buildSuccessResult(genealogiesList);
	}

}
