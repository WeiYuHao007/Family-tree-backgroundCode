package com.orange.familyTree.service;

import com.orange.familyTree.dao.mysql.UserRepository;
import com.orange.familyTree.entity.mysql.User;
import com.orange.familyTree.pojo.RegisterVO;
import com.orange.familyTree.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.LoginVO;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDO findUser(LoginVO loginVO) throws MyCypherException{
		// 登入账号
		try {
			if(loginVO.getPhoneNum() != null) {
				// 通过电话号码登入
				User user = userRepository.findUserByPhoneNumAndPassword(loginVO.getPhoneNum(), loginVO.getPassword());
				UserDO userDetail = UserDO.changeEToDO(user);
				return userDetail;
			}
			else {
				User user = userRepository.findUserByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
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

	@Override
	public void registerUser(RegisterVO register) throws MyCypherException {
		// 注册账号
		try {
			userRepository.registerUser(register.getNickname(), register.getPhoneNum(), register.getEmail(),
					register.getPassword());
		}
		catch(Exception ex) {
			throw new MyCypherException("注册失败");
		}
	}
	
	@Override
	public void changePassword(LoginVO accountViewDetail) throws MyCypherException {
		// TODO Auto-generated method stub
		
	}

}
