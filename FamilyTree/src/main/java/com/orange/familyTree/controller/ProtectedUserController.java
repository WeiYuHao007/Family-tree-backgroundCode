package com.orange.familyTree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import com.orange.familyTree.pojo.UserDO;
import com.orange.familyTree.pojo.UserVO;
import com.orange.familyTree.pojo.specialPojo.UserShowVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api")
public class ProtectedUserController {

	// 权限条件：处于登录状态
	
	@Autowired
	private UserService userService;

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

	// 获取UserVO,用于用户展示个人资料卡
	// 获取UserShow，用于渲染界面
	@GetMapping(value = "/user/{user-nickname}/info")
	public Result getUserInfo(HttpServletRequest request, @PathVariable("user-nickname") String userNickname,
							  @RequestParam("all") Boolean all) throws MySQLException {
		HttpSession session = request.getSession(false);
		if(all) {
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			UserDO userDO = userService.getUserById(userId);
			UserVO userVO = UserDO.changeToVo(userDO);
			return ResultFactory.buildSuccessResult(userVO);
		}
		else {
			String nickname = userNickname;
			UserDO userDO = userService.getUserByNickname(nickname);
			// 在data中返回UserShow实体
			UserShowVO userShow = UserDO.changeToShow(userDO);
			return ResultFactory.buildSuccessResult(userShow);
		}
	}

	// 获得用户关注的所有图谱的更新动态
	@GetMapping(value = "/user/focus-trees/update-record")
	public Result getAllGenealogyUpdateRecord(HttpServletRequest request) throws MySQLException {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		ArrayList<GenealogyUpdateRecordVO> updateRecordsVO = userService.getGenealogyUpdateRecord(userId);
		return ResultFactory.buildSuccessResult(updateRecordsVO);
	}

	// 申请关注指定图谱
	@PostMapping(value = "/tree/{tree-name}/application")
	public Result applyForGenealogy(@PathVariable("tree-name") String genealogyName, HttpServletRequest request,
									@RequestBody String applicationComment)
			throws MySQLException {
		HttpSession session = request.getSession(false);
		Long userId = (Long) session.getAttribute("SESSION_USERID");
		UserDO userDO = userService.getUserById(userId);
		userService.applyForGenealogy(genealogyName, userDO.getUserNickname(), applicationComment);
		return ResultFactory.buildSuccessResult("申请成功。");
	}
}
