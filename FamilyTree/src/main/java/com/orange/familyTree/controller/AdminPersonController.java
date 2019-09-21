package com.orange.familyTree.controller;

import com.orange.familyTree.dao.mysql.UserMySQLRepository;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.specialPojo.RelationshipVO;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api")
public class AdminPersonController {
	// 权限条件： 图谱管理员
	
	@Autowired
	private PersonService personService;

	@Autowired
	private UserMySQLRepository userMySQLRepository;

	// 增加节点
	@PostMapping(value = "/tree/{tree-name}/node")
	public Result addPerson(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
							@RequestBody PersonVO personVO) {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		String userNickname = userMySQLRepository.findUserNicknameById(userId);
		return personService.createPerson(genealogyName, personVO, userNickname);
	}
	
	// 增加关系
	@PostMapping(value = "/tree/{tree-name}/relationship")
	public Result addRelationship(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
			@RequestBody RelationshipVO relationshipVO) {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		String userNickname = userMySQLRepository.findUserNicknameById(userId);
		return personService.createRelationship(genealogyName, relationshipVO, userNickname);
	}

	// 修改节点信息
	@PutMapping(value = "/tree/{tree-name}/node-info")
	public Result changePersonInfo(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
								   @RequestBody PersonVO personVO) {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		String userNickname = userMySQLRepository.findUserNicknameById(userId);
		return personService.changePersonInfo(genealogyName, personVO, userNickname);
	}

	// 删除节点
	@DeleteMapping(value = "/tree/{tree-name}/node/{nodeName}")
	public Result deletePerson(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
							   @PathVariable("nodeName") String personName) {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		String userNickname = userMySQLRepository.findUserNicknameById(userId);
		return personService.deletePerson(genealogyName, personName, userNickname);
	}

	// 删除关系
	@DeleteMapping(value = "/tree/{tree-name}/relationship")
	public Result deleteRelationship(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
			@RequestParam("source") String sourceName, @RequestParam("target") String targetName) {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		String userNickname = userMySQLRepository.findUserNicknameById(userId);
		return personService.deleteRelationship(genealogyName, sourceName, targetName, userNickname);
	}

}
