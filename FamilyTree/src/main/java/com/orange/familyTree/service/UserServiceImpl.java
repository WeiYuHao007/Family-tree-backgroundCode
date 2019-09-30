package com.orange.familyTree.service;

import com.orange.familyTree.dao.mysql.GenealogyFocusApplicationMySQLRepository;
import com.orange.familyTree.dao.mysql.GenealogyMySQLRepository;
import com.orange.familyTree.dao.mysql.GenealogyUpdateRecordMySQLRepository;
import com.orange.familyTree.dao.mysql.UserMySQLRepository;
import com.orange.familyTree.dao.neo4j.UserNeo4jRepository;
import com.orange.familyTree.entity.mysql.GenealogyFocusApplication;
import com.orange.familyTree.entity.mysql.GenealogyUpdateRecord;
import com.orange.familyTree.entity.mysql.UserMySQL;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyFocusApplicationVO;
import com.orange.familyTree.pojo.GenealogyUpdateRecordVO;
import com.orange.familyTree.pojo.specialPojo.RegisterVO;
import com.orange.familyTree.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.specialPojo.LoginVO;

import java.util.ArrayList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMySQLRepository userMySQLRepository;

	@Autowired
	private UserNeo4jRepository userNeo4jRepository;

	@Autowired
	private GenealogyUpdateRecordMySQLRepository genealogyUpdateRecordMySQLRepository;

	@Autowired
	private GenealogyFocusApplicationMySQLRepository genealogyFocusApplicationMySQLRepository;

	@Autowired
	private GenealogyMySQLRepository genealogyMySQLRepository;

	@Override
	public Boolean CheckUserInfoDuplicated(RegisterVO register) throws MySQLException {
		try {
			UserMySQL user = userMySQLRepository.checkUserInfoDuplicated(register.getNickname(),
					register.getPhoneNum(), register.getEmail());
			if(user != null) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("验证用户昵称是否存在出现异常。");
		}
	}

	// 登入账号
	@Override
	public UserDO getUser(LoginVO loginVO) throws MyCypherException{
		try {
			if(loginVO.getPhoneNum() != null) {
				// 通过电话号码登入
				UserMySQL userMySQL = userMySQLRepository.findUserByPhoneNumAndPassword(loginVO.getPhoneNum(), loginVO.getPassword());
				if(userMySQL.getUserId() != null) {
					UserDO userDetail = UserMySQL.changeToDO(userMySQL);
					return userDetail;
				}
				throw new MySQLException();
			}
			else {
				UserMySQL userMySQL = userMySQLRepository.findUserByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());
				UserDO userDetail = UserMySQL.changeToDO(userMySQL);
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
			UserMySQL userMySQL = userMySQLRepository.findUserById(userId);
			UserDO userDO = UserMySQL.changeToDO(userMySQL);
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
			UserMySQL userMySQL = userMySQLRepository.findUserByNickName(userNickname);
			UserDO userDO = UserMySQL.changeToDO(userMySQL);
			return userDO;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("账号查询异常。");
		}
	}

	// 查询用户头像文件名
	@Override
	public String getUserAvatarFileName(String userNickname) throws MySQLException {
		try{
			return userMySQLRepository.findUserAvatar(userNickname);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("查询用户头像文件名异常。");
		}
	}

	// 注册账号
	@Override
	public void registerUser(RegisterVO register) throws MyCypherException {
		try {
			System.out.println(register.getNickname());
			userMySQLRepository.registerUser(register.getNickname(), register.getPhoneNum(), register.getEmail(),
					register.getPassword());
			Long userId = userMySQLRepository.findUserIdByNickname(register.getNickname());
			userNeo4jRepository.createUser(userId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("注册异常。");
		}
	}

	// 更改密码
	@Override
	public void changePassword(Long userId, String oldPassword, String newPassword) throws MySQLException {
		// 验证原账户身份
		// 更改密码
		try {
			userMySQLRepository.changePassword(userId, oldPassword, newPassword);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("更改密码异常。");
		}
	}

	// 修改用户头像文件名称
	@Override
	public void changeUserAvatarFileName(String userNickname, String newUserAvatarFileName) throws MySQLException {
		try{
			userMySQLRepository.changeUserAvatar(userNickname, newUserAvatarFileName);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("修改用户头像文件名称异常。");
		}
	}

	// 修改用户昵称和个人简介
	@Override
	public void changeUserNicknameAndIntroduction(String userNickname, String newUserNickname, String newUserIntroduction) throws MySQLException {
		try{
			Long userId = userMySQLRepository.findUserIdByNickname(userNickname);
			if(userId != null) {
				userMySQLRepository.changeUserNicknameAndIntroduction(userNickname, newUserNickname, newUserIntroduction);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("修改用户昵称和个人简介异常。");
		}
	}

	// 获得用户关注的所有图谱的更新动态
	@Override
	public ArrayList<GenealogyUpdateRecordVO> getGenealogyUpdateRecord(Long userId) throws MySQLException {
		// 获得用户id
		// 在图数据库中查询用户关注的所有图谱的id
		try {
			ArrayList<Long> genealogiesId = userNeo4jRepository.findAllFocusGenealogy(userId);
			// 在updateRecord表中获得更新动态
			ArrayList<GenealogyUpdateRecord> updateRecords =
					genealogyUpdateRecordMySQLRepository.findUpdateRecordByGenealogiesId(genealogiesId);
			int updateRecordsLength = updateRecords.size();
			ArrayList<GenealogyUpdateRecordVO> updateRecordVOs= new ArrayList<>();
			for(int i = 0; i < updateRecordsLength; i++) {
				updateRecordVOs.add(GenealogyUpdateRecord.changeToVO(updateRecords.get(i)));
			}
			return updateRecordVOs;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("获得用户关注的所有图谱的更新动态异常。");
		}
	}

	// 获得用户管理的所有图谱的关注请求
	@Override
	public ArrayList<GenealogyFocusApplicationVO> getGenealogyFocusApplicationByUserId(Long userId) throws MyCypherException {
		try {
			ArrayList<Long> adminGenealogiesId = userNeo4jRepository.findAllAdminGenealogy(userId);
			if(!adminGenealogiesId.isEmpty()) {
				ArrayList<GenealogyFocusApplication> applications =
						genealogyFocusApplicationMySQLRepository.findApplicationByGenealogiesId(adminGenealogiesId);
				ArrayList<GenealogyFocusApplicationVO> applicationVOs = new ArrayList<>();
				int length = applications.size();
				for(int i = 0; i< length; i++) {
					// 后期可以优化为查询图谱（用户）ID与名称的map
					GenealogyFocusApplication application = applications.get(i);
					String genealogyName = genealogyMySQLRepository.findGenealogyNameById(application.getGenealogyId());
					String userNickname = userMySQLRepository.findUserNicknameById(application.getUserId());
					applicationVOs.add(new GenealogyFocusApplicationVO(genealogyName, userNickname,
							application.getApplicationComment()));
				}
				return applicationVOs;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("获得用户管理的所有图谱的关注请求异常。");
		}
	}

	// 获得管理图谱的关注申请数量
	@Override
	public Integer getAdminGenealogyFocusApplicationNum(Long userId) throws MySQLException {
		try {
			ArrayList<Long> adminGenealogiesId = userNeo4jRepository.findAllAdminGenealogy(userId);
			if(!adminGenealogiesId.isEmpty()) {
				ArrayList<GenealogyFocusApplication> applications =
						genealogyFocusApplicationMySQLRepository.findApplicationByGenealogiesId(adminGenealogiesId);
				return applications.size();
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("获得用户管理的所有图谱的关注请求异常。");
		}
	}

	// 申请关注指定图谱
	@Override
	public void applyForGenealogy(String genealogyName, String userNickname, String applicationComment) throws MySQLException {
		// 查询图谱与用户ID
		// 在focusApplication表中登记
		// 在Neo4j中创建关注状态
		try {
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Long userId = userMySQLRepository.findUserIdByNickname(userNickname);
			genealogyFocusApplicationMySQLRepository.createApplication(genealogyId, userId, applicationComment);
			userNeo4jRepository.applyForFocusOnGenealogy(genealogyId, userId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("申请关注指定图谱异常。");
		}
	}

}
