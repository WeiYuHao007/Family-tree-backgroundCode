package com.orange.familyTree.service;

import com.orange.familyTree.dao.mysql.UserMySQLRepository;
import com.orange.familyTree.entity.mysql.User;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.specialPojo.RegisterVO;
import com.orange.familyTree.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.specialPojo.LoginVO;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMySQLRepository userMySQLRepository;


	// 登入账号
	@Override
	public UserDO getUser(LoginVO loginVO) throws MyCypherException{
		try {
			if(loginVO.getPhoneNum() != null) {
				// 通过电话号码登入
				User user = userMySQLRepository.findUserByPhoneNumAndPassword(loginVO.getPhoneNum(), loginVO.getPassword());
				UserDO userDetail = UserDO.changeEToDO(user);
				return userDetail;
			}
			else {
				User user = userMySQLRepository.findUserByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
				UserDO userDetail = UserDO.changeEToDO(user);
				return userDetail;
			}
		}
		catch(Exception ex){
			// 账号登入失败
			ex.printStackTrace();
			throw new MyCypherException("账号或密码错误，请重新输入。");
		}
	}

	// 通过用户Id获得用户
	@Override
	public UserDO getUserById(Long userId) throws MySQLException {
		try {
			User user = userMySQLRepository.findUserById(userId);
			UserDO userDO = UserDO.changeEToDO(user);
			return userDO;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("账号查询异常。");
		}
	}

	// 通过昵称获得用户
	@Override
	public UserDO getUserByNickname(String userNickname) throws MySQLException {
		try {
			User user = userMySQLRepository.findUserByNickName(userNickname);
			UserDO userDO = UserDO.changeEToDO(user);
			return userDO;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("账号查询异常。");
		}
	}

	// 注册账号
	@Override
	public void registerUser(RegisterVO register) throws MyCypherException {
		try {
			userMySQLRepository.registerUser(register.getNickname(), register.getPhoneNum(), register.getEmail(),
					register.getPassword());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("注册异常。");
		}
	}

	// 更改密码
	@Override
	public void changePassword(LoginVO accountViewDetail) throws MyCypherException {
		// TODO Auto-generated method stub
		
	}

}
