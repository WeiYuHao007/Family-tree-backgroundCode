package com.orange.familyTree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.exceptions.UserException;
import com.orange.familyTree.pojo.*;
import com.orange.familyTree.pojo.specialPojo.ChangePasswordVO;
import com.orange.familyTree.pojo.specialPojo.LoginVO;
import com.orange.familyTree.pojo.specialPojo.RegisterVO;
import com.orange.familyTree.pojo.specialPojo.UserShowVO;
import com.orange.familyTree.pojo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;
import com.orange.familyTree.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class PublicUserController {
	// 权限条件：无

	@Autowired
	private UserService userService;

	// 用户登入
	@PostMapping(value="/user/status")
	public Result signIn(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody LoginVO loginVO) {
		UserDO userDO = userService.getUser(loginVO);
		//设置为登录状态
		HttpSession session = request.getSession(true);
		Long userId = userDO.getUserId();
		String userNickname = userDO.getUserNickname();
		session.setAttribute("SESSION_USERID", userId);
		session.setAttribute("SESSION_NICKNAME", userNickname);
		//会话存在时间为一小时
		session.setMaxInactiveInterval(3600);
		//将数据库映射对象转化为视图映射对象返回
		Result result = ResultFactory.buildSuccessResult("登陆成功。", userDO.getUserNickname());
		return result;
	}
	
	// 账户注册
	@PostMapping(value = "/user")
	public Result register(@RequestBody RegisterVO register) throws MySQLException {
		// 验证用户昵称是否存在
		if(!userService.findWhetherHaveUserNickname(register.getNickname())) {
			userService.registerUser(register);
			return ResultFactory.buildSuccessResult("注册成功。");
		}
		else {
			return ResultFactory.buildFailResult("用户昵称已存在，请重新输入。");
		}
	}


	// 修改密码
	@PatchMapping(value = "/user/password")
	public Result changePassword(@RequestBody ChangePasswordVO changePasswordVO) throws MySQLException {
		userService.changePassword(changePasswordVO.getTelephoneNum(), changePasswordVO.getOldPassword(),
				changePasswordVO.getNewPassword());
		return ResultFactory.buildSuccessResult("修改成功");
	}

	// 获取UserVO,用于用户展示个人资料卡
	@GetMapping(value = "/user/{user-nickname}/info-show")
	public Result getUserInfo(@PathVariable("user-nickname") String userNickname)
			throws MySQLException {
		try {
			String nickname = userNickname;
			UserDO userDO = userService.getUserByNickname(nickname);
			// 在data中返回UserShow实体
			UserShowVO userShow = UserDO.changeToShow(userDO);
			return ResultFactory.buildSuccessResult(userShow);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return ResultFactory.buildFailResult("获取用户界面渲染信息异常。");
		}
	}

}
