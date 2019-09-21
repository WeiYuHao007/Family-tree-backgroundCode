package com.orange.familyTree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.dao.mysql.GenealogyMySQLRepository;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyFocusApplicationVO;
import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import com.orange.familyTree.pojo.UserDO;
import com.orange.familyTree.pojo.UserVO;
import com.orange.familyTree.pojo.specialPojo.ApplicationVO;
import com.orange.familyTree.pojo.specialPojo.UserShowVO;

import com.orange.familyTree.service.GenealogyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProtectedUserController {

	// 权限条件：处于登录状态
	
	@Autowired
	private UserService userService;

	@Autowired
	private GenealogyService genealogyService;

	// 注销账号
	@DeleteMapping(value = "/user/status")
	public Result logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResultFactory.buildSuccessResult("注销成功。");
	}

	// 获取登录账号的昵称
	@GetMapping(value= "/user/header-info")
	public Result getNickName(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			if(session == null) {
				return null;
			}
			String userNickname = (String) session.getAttribute("SESSION_NICKNAME");
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			Integer messageNum = userService.getAdminGenealogyFocusApplicationNum(userId);
			Object result = new Object[]{userNickname, messageNum};
			return ResultFactory.buildSuccessResult("请求成功", result);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// 获取UserVO,用于用户展示个人资料卡
	// 获取UserShow，用于渲染界面(迭代时应该考虑分离俩部分)
	@GetMapping(value = "/user/{user-nickname}/info")
	public Result getUserInfo(HttpServletRequest request, @PathVariable("user-nickname") String userNickname,
							  @RequestParam("all") Boolean all) throws MySQLException {
		HttpSession session = request.getSession(false);
		if(all) {
			String session_nickname = (String) session.getAttribute("SESSION_NICKNAME");
			if(userNickname.equals(session_nickname)) {
				Long userId = (Long) session.getAttribute("SESSION_USERID");
				UserDO userDO = userService.getUserById(userId);
				UserVO userVO = UserDO.changeToVo(userDO);
				return ResultFactory.buildSuccessResult(userVO);
			}
			else {
				return null;
			}
		}
		else {
			String nickname = userNickname;
			UserDO userDO = userService.getUserByNickname(nickname);
			// 在data中返回UserShow实体
			UserShowVO userShow = UserDO.changeToShow(userDO);
			return ResultFactory.buildSuccessResult(userShow);
		}
	}

	// 获得登录用户关注的所有图谱名称
	@GetMapping(value="/user/{user-nickname}/trees")
	public Result findGenealogyByName(HttpServletRequest request, @PathVariable("user-nickname") String userNickname) {
		HttpSession session = request.getSession(false);
		String sessionNickname = (String) session.getAttribute("SESSION_NICKNAME");
		// 数据敏感，验证请求名称与登录名称的差异
		if(userNickname.equals(sessionNickname)) {
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			List<String> nameList = genealogyService.findAllGenealogy(userId);
			return ResultFactory.buildSuccessResult(nameList);
		}
		else {
			return null;
		}
	}

	// 获得用户关注的所有图谱的更新动态
	@GetMapping(value = "/user/{user-nickname}/focus-trees/update-record")
	public Result getAllGenealogyUpdateRecord(HttpServletRequest request,
											  @PathVariable("user-nickname") String userNickname)
			throws MySQLException {
		HttpSession session = request.getSession(false);
		String sessionNickname = (String) session.getAttribute("SESSION_NICKNAME");
		// 防止登录用户名称与请求名称不符合
		if(userNickname.equals(sessionNickname)) {
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			if(genealogyService.findWhetherHaveFocusGenealogy(userId)) {
				ArrayList<GenealogyUpdateRecordVO> updateRecordsVO = userService.getGenealogyUpdateRecord(userId);
				return ResultFactory.buildSuccessResult(updateRecordsVO);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	// 获得登录用户管理的所有图谱的关注请求
	@GetMapping(value = "/user/{user-nickname}/admin-trees/application")
	public Result getAllAdminTreeApplication(HttpServletRequest request,
											 @PathVariable("user-nickname") String userNickname) {
		HttpSession session = request.getSession(false);
		String sessionNickname = (String) session.getAttribute("SESSION_NICKNAME");
		if(userNickname.equals(sessionNickname)) {
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			ArrayList<GenealogyFocusApplicationVO> applicationVOs = userService.getGenealogyFocusApplicationByUserId(userId);
			return ResultFactory.buildSuccessResult(applicationVOs);
		}
		else {
			return null;
		}
	}

	// 申请关注指定图谱
	@PostMapping(value = "/tree/{tree-name}/application")
	public Result applyForGenealogy(@PathVariable("tree-name") String genealogyName, HttpServletRequest request,
									@RequestBody ApplicationVO application)
			throws MySQLException {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		List<String> focusedGenealogy =  genealogyService.findAllGenealogy(userId);
		int length = focusedGenealogy.size();
		for(int i = 0; i < length; i++) {
			// 用户已经该图谱，关闭该操作
			if(focusedGenealogy.get(i).equals(genealogyName)) {
				return ResultFactory.buildFailResult("您已经关注该图谱。");
			}
		}
		UserDO userDO = userService.getUserById(userId);
		userService.applyForGenealogy(genealogyName, userDO.getUserNickname(), application.getComment());
		return ResultFactory.buildSuccessResult("申请成功。");
	}
}
