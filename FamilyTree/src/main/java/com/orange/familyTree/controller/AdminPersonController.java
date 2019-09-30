package com.orange.familyTree.controller;

import com.orange.familyTree.dao.mysql.UserMySQLRepository;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.util.ResultFactory;
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
							@RequestBody PersonVO personVO) throws MySQLException {
		try {
			HttpSession session = request.getSession(false);
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			String userNickname = userMySQLRepository.findUserNicknameById(userId);
			if (!personService.findPersonWhetherExist(genealogyName, personVO.getName())) {
				return personService.createPerson(genealogyName, personVO, userNickname);
			}
			else {
				return ResultFactory.buildFailResult("名称不存在，请重新输入。");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("增加节点异常。");
		}
	}
	
	// 增加关系
	@PostMapping(value = "/tree/{tree-name}/relationship")
	public Result addRelationship(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
			@RequestBody RelationshipVO relationshipVO) throws MySQLException {
		try {
			HttpSession session = request.getSession(false);
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			String userNickname = userMySQLRepository.findUserNicknameById(userId);
			if(personService.findPersonWhetherExist(genealogyName, relationshipVO.getSource())
					&& personService.findPersonWhetherExist(genealogyName, relationshipVO.getTarget())) {
				return personService.createRelationship(genealogyName, relationshipVO, userNickname);
			}
			else {
				return ResultFactory.buildFailResult("名称不存在，请重新输入。");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("增加关系异常。");
		}
	}

	// 修改节点信息
	@PutMapping(value = "/tree/{tree-name}/node-info")
	public Result changePersonInfo(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
								   @RequestBody PersonVO personVO) throws MySQLException {
		try {
			HttpSession session = request.getSession(false);
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			String userNickname = userMySQLRepository.findUserNicknameById(userId);
			if (personService.findPersonWhetherExist(genealogyName, personVO.getName())) {
				return personService.changePersonInfo(genealogyName, personVO, userNickname);
			}
			else {
				return ResultFactory.buildFailResult("名称不存在，请重新输入。");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("修改节点信息异常。");
		}
	}

	// 删除节点
	@DeleteMapping(value = "/tree/{tree-name}/node/{nodeName}")
	public Result deletePerson(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
							   @PathVariable("nodeName") String personName) throws MySQLException {
		try{
			HttpSession session = request.getSession(false);
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			String userNickname = userMySQLRepository.findUserNicknameById(userId);
			if (personService.findPersonWhetherExist(genealogyName, personName)) {
				return personService.deletePerson(genealogyName, personName, userNickname);
			}
			else {
				return ResultFactory.buildFailResult("名称不存在，请重新输入。");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("删除节点异常。");
		}
	}

	// 删除关系
	@DeleteMapping(value = "/tree/{tree-name}/relationship")
	public Result deleteRelationship(HttpServletRequest request, @PathVariable("tree-name") String genealogyName,
			@RequestParam("source") String sourceName, @RequestParam("target") String targetName) throws MySQLException {
		try{
			HttpSession session = request.getSession(false);
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			String userNickname = userMySQLRepository.findUserNicknameById(userId);
			if(personService.findPersonWhetherExist(genealogyName, sourceName)
					&& personService.findPersonWhetherExist(genealogyName, targetName)) {
				return personService.deleteRelationship(genealogyName, sourceName, targetName, userNickname);
			}
			else {
				return ResultFactory.buildFailResult("名称不存在，请重新输入。");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("删除关系异常");
		}
	}

}
