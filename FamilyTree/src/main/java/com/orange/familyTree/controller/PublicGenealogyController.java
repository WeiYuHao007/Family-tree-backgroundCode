package com.orange.familyTree.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.entity.mysql.GenealogyMySQL;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.specialPojo.NewGenealogyVO;
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
	
	// 关键词搜索图谱（显示图谱的不敏感信息）
	@GetMapping(value = "/trees/info")
	public Result keywordSearch(@RequestParam("keyword") String keyword, @RequestParam("pageNum") Integer pageNum)
			throws MySQLException {
		ArrayList<GenealogyMySQL> genealogiesList= genealogyService.keywordSearch(keyword, pageNum);
		return ResultFactory.buildSuccessResult(genealogiesList);
	}

	// 创建图谱
	@PostMapping(value = "/tree")
	public Result createGenealogy(HttpServletRequest request, @RequestBody NewGenealogyVO newGenealogyVO) throws MySQLException {
		// 验证新图谱名称是否存在
		if(!genealogyService.findWhetherHaveGenealogyName(newGenealogyVO.getNewGenealogyName())) {
			HttpSession session = request.getSession(false);
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			PersonVO personVO = new PersonVO(newGenealogyVO.getDefaultCenterNodeName(), newGenealogyVO.getNodeBirthTime(),
					newGenealogyVO.getNodeDeathTime(), newGenealogyVO.getNodeMajorAchievements());
			genealogyService.createNewGenealogy(userId, newGenealogyVO.getNewGenealogyName(),
					newGenealogyVO.getNewGenealogyDescription(), personVO);
			return ResultFactory.buildSuccessResult("创建成功。");
		}
		else {
			return ResultFactory.buildFailResult("族谱名已存在，请重新输入。");
		}
	}
}
