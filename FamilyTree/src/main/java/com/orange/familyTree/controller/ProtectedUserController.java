package com.orange.familyTree.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.config.MyWebMvcConfig;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.exceptions.UserException;
import com.orange.familyTree.pojo.GenealogyFocusApplicationVO;
import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import com.orange.familyTree.pojo.UserDO;
import com.orange.familyTree.pojo.UserVO;
import com.orange.familyTree.pojo.specialPojo.ApplicationVO;
import com.orange.familyTree.pojo.specialPojo.NewUserNicknameAndIntroduction;

import com.orange.familyTree.pojo.util.FileUtil;
import com.orange.familyTree.service.GenealogyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProtectedUserController {

	// 权限条件：处于登录状态

	@Autowired
	private MyWebMvcConfig myWebMvcConfig;

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

	// 获取登录账号头信息
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
	@GetMapping(value = "/user/{user-nickname}/info-vo")
	public Result getUserInfo(HttpServletRequest request, @PathVariable("user-nickname") String userNickname)
			throws MySQLException {
		try {
			HttpSession session = request.getSession(false);
			String session_nickname = (String) session.getAttribute("SESSION_NICKNAME");
			if (userNickname.equals(session_nickname)) {
				Long userId = (Long) session.getAttribute("SESSION_USERID");
				UserDO userDO = userService.getUserById(userId);
				UserVO userVO = UserDO.changeToVo(userDO);
				return ResultFactory.buildSuccessResult(userVO);
			} else {
				return ResultFactory.buildFailResult("无法获得他人的个人资料卡。");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return ResultFactory.buildFailResult("获取个人资料卡信息异常。");
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

	// 上传用户头像
	@PostMapping("/user/avatar")
	public Result postUserAvatar(@RequestBody MultipartFile avatar, HttpServletRequest request) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			String userNickname = (String) session.getAttribute("SESSION_NICKNAME");
			String userAvatarFileName = userService.getUserAvatarFileName(userNickname);
			String uploadFolder = myWebMvcConfig.getUploadFolder();
			if(userAvatarFileName != null) {
				// 该用户曾经提交过头像
				File avatarFile = new File(uploadFolder, userAvatarFileName);
				if(!avatarFile.exists()) {
					// 有文件名称但是文件不存在了
					avatarFile.createNewFile();
				}
				avatarFile.createNewFile();
				avatar.transferTo(avatarFile);
				return ResultFactory.buildSuccessResult("上传成功。");
			}
			// 该用户未曾提交过头像
			String fileName = avatar.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			Long userId = (Long) session.getAttribute("SESSION_USERID");
			File newAvatarFile = new File(uploadFolder, FileUtil.getUUID()+ userId + fileType);
			if(!newAvatarFile.exists()) {
				newAvatarFile.createNewFile();
			}
			userService.changeUserAvatarFileName(userNickname, newAvatarFile.getName());
			avatar.transferTo(newAvatarFile);
			return ResultFactory.buildSuccessResult("上传成功。");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new UserException("上传失败");
		}
	}

	// 修改用户昵称和个人简介
	@PostMapping("/user/nickname-introduction")
	public Result changeUserNicknameAndIntroduction(HttpServletRequest request, @RequestBody NewUserNicknameAndIntroduction newInfo) {
		try{
			HttpSession session = request.getSession(false);
			String userNickname = (String) session.getAttribute("SESSION_NICKNAME");
			userService.changeUserNicknameAndIntroduction(userNickname, newInfo.getNewNickname(), newInfo.getNewIntroduction());
			return ResultFactory.buildSuccessResult("修改成功。");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return ResultFactory.buildFailResult("修改失败。");
		}
	}
}
