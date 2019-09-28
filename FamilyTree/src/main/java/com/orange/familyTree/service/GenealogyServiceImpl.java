package com.orange.familyTree.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.orange.familyTree.dao.mysql.GenealogyFocusApplicationMySQLRepository;
import com.orange.familyTree.dao.mysql.GenealogyMySQLRepository;
import com.orange.familyTree.dao.mysql.GenealogyUpdateRecordMySQLRepository;
import com.orange.familyTree.dao.mysql.UserMySQLRepository;
import com.orange.familyTree.dao.neo4j.PersonNeo4jRepository;
import com.orange.familyTree.dao.neo4j.UserNeo4jRepository;
import com.orange.familyTree.entity.mysql.GenealogyFocusApplication;
import com.orange.familyTree.entity.mysql.GenealogyMySQL;
import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.exceptions.MySQLException;
import com.orange.familyTree.pojo.GenealogyFocusApplicationVO;
import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.util.UpdateRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.dao.neo4j.GenealogyNeo4jRepository;
import com.orange.familyTree.exceptions.MyCypherException;


@Service
@Transactional
public class GenealogyServiceImpl implements GenealogyService {

	@Autowired
	private GenealogyNeo4jRepository genealogyNeo4jRepository;

	@Autowired
	private GenealogyMySQLRepository genealogyMySQLRepository;

	@Autowired
	private GenealogyFocusApplicationMySQLRepository genealogyFocusApplicationMySQLRepository;

	@Autowired
	private GenealogyUpdateRecordMySQLRepository genealogyUpdateRecordMySQLRepository;

	@Autowired
	private UserMySQLRepository userMySQLRepository;

	@Autowired
	private UserNeo4jRepository userNeo4jRepository;

	@Autowired
	private PersonNeo4jRepository personNeo4jRepository;

	// 查询目标族谱的关注者
	@Override
	public List<Long> findGenealogyFollowersIdByName(String genealogyName) throws MyCypherException {
		try {
			List<Long> followersIdList = genealogyNeo4jRepository.findGenealogyFollowersByName(genealogyName);
			return followersIdList;
		}
		catch(Exception ex) {
			throw new MyCypherException("查询图谱关注者Id异常。");
		}
	}

	// 查询目标族谱的所有关注者名称
	@Override
	public List<String> findGenealogyFollowersName(String genealogyName) throws MyCypherException {
		try {
			List<Long> followersIdList = genealogyNeo4jRepository.findGenealogyFollowersByName(genealogyName);
			List<String> followersNicknameList = userMySQLRepository.findUsersNicknameByIds(followersIdList);
			return followersNicknameList;
		}
		catch(Exception ex) {
			throw new MyCypherException("查询图谱关注者昵称异常。");
		}
	}

	// 查询目标族谱的所有普通关注者昵称
	@Override
	public List<String> findGenealogyOrdinaryFollowers(String genealogyName) throws MyCypherException {
		try {
			List<Long> followersIdList = genealogyNeo4jRepository.findGenealogyOrdinaryFollowers(genealogyName);
			List<String> followersNicknameList = userMySQLRepository.findUsersNicknameByIds(followersIdList);
			return followersNicknameList;
		}
		catch(Exception ex) {
			throw new MyCypherException("查询目标族谱的所有普通关注者昵称异常。");
		}
	}

	// 检查图谱名称是否存在
	@Override
	public Boolean findWhetherHaveGenealogyName(String genealogyName) throws MySQLException {
		try {
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			if(genealogyId != null) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("验证图谱名称是否存在出现异常。");
		}
	}

	// 查询图谱默认中心节点名称
	@Override
	public String getGenealogyDefaultCenterPerson(String genealogyName) throws MySQLException{
		try{
			String name = genealogyMySQLRepository.getGenealogyDefaultCenterPerson(genealogyName);
			return name;
		}
		catch(Exception ex) {
			throw new MySQLException("查询图谱默认中心节点异常。");
		}
	}

	// 查询目标族谱的管理员
	@Override
	public List<Long> findGenealogyAdminsByName(String genealogyName) throws MyCypherException {
		try {
			List<Long> adminsList = genealogyNeo4jRepository.findGenealogyAdminsByName(genealogyName);
			return adminsList;
		}
		catch(Exception ex) {
			throw new MyCypherException("查询图谱管理员异常。");
		}
	}

	// 查询用户关注的图谱
	@Override
	public List<String> findAllGenealogy(Long userId) throws MyCypherException {
		try {
			List<String> nameList = genealogyNeo4jRepository.findAllGenealogy(userId);
			return nameList;
		}
		catch(Exception ex){
			throw new MyCypherException("读取关注图谱异常。");
		}
	}

	// 查询用户是否有关注图谱
	@Override
	public Boolean findWhetherHaveFocusGenealogy(Long userId) throws MyCypherException {
		try {
			List<String> nameList = genealogyNeo4jRepository.findAllGenealogy(userId);
			if(nameList.isEmpty()) {
				return false;
			}
			return true;
		}
		catch(Exception ex){
			throw new MyCypherException("读取关注图谱异常。");
		}
	}

	// 查询指定图谱拥有的所有节点名称
	@Override
	public List<String> findPersonsByGenealogyName(String genealogyName) throws MyCypherException{
		try {
			List<String> nameList = genealogyNeo4jRepository.findPersonsByGenealogyName(genealogyName);
			return nameList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("指定图谱所拥有的节点姓名查询异常。");
		}
	}

	// 查询图谱详细信息（包含默认节点）
	@Override
	public GenealogyMySQL findGenealogyInfo(String genealogyName) throws MySQLException {
		try {
			// 在genealogy表中获得图谱信息
			GenealogyMySQL genealogy = genealogyMySQLRepository.findGenealogyByName(genealogyName);
			return genealogy;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("获得图谱信息异常。");
		}
	}

	// 关键词搜索图谱（显示公开信息）
	@Override
	public ArrayList<GenealogyMySQL> keywordSearch(String keyword, Integer pageNum) throws MySQLException,
			IllegalArgumentException {
		try {
			if(pageNum >= 1 && keyword != "") {
				keyword = keyword.replace("", "%");
				Integer start = 5 * pageNum - 5;
				ArrayList<GenealogyMySQL> genealogyList = genealogyMySQLRepository.keywordSearchGenealogy(keyword, start);
				if(genealogyList.isEmpty() && pageNum == 1) {
					throw new MySQLException("无任何匹配的图谱。");
				}
				if(genealogyList.isEmpty() && pageNum >= 1) {
					throw new MySQLException("已经是最后一页。");
				}
				return genealogyList;
			} else {
				return null;
			}
		}
		catch(IllegalArgumentException ex) {
			throw new MySQLException("页数不能为小于一的整数。");
		}
		catch(MySQLException ex) {
			throw new MySQLException(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("关键词搜索图谱异常。");
		}
	}

	// 查看申请关注的用户名单
	@Override
	public ArrayList<GenealogyFocusApplicationVO> getApplicationList(String genealogyName) throws MySQLException {
		// 将名单实体查询出
		// 将id参数前往数据库中转换成名称
		// 构建VO实体返回
		try {
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			ArrayList<GenealogyFocusApplication> focusApplicationsList =
					genealogyFocusApplicationMySQLRepository.findApplicationByGenealogyId(genealogyId);
			int focusApplicationsListLength = focusApplicationsList.size();
			ArrayList<GenealogyFocusApplicationVO> focusApplicationVOsList = new ArrayList<>();
			for(int i = 0; i < focusApplicationsListLength; i++) {
				GenealogyFocusApplication focusApplication = focusApplicationsList.get(i);
				Long tempUserId = focusApplication.getUserId();
				Long tempGenealogyId = focusApplication.getGenealogyId();
				String temUserNickname = userMySQLRepository.findUserNicknameById(tempUserId);
				String temGenealogyName = genealogyMySQLRepository.findGenealogyNameById(tempGenealogyId);
				GenealogyFocusApplicationVO focusApplicationVO = new GenealogyFocusApplicationVO(temGenealogyName,
						temUserNickname, focusApplication.getApplicationComment());
				focusApplicationVOsList.add(focusApplicationVO);
			}
			return focusApplicationVOsList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("查询申请关注用户名单异常。");
		}
	}

	// 修改图谱名称
	@Override
	public Boolean changeGenealogyName(String genealogyName, String newName) throws MyCypherException, MySQLException {
		try {
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			if(genealogyId != null) {
				genealogyMySQLRepository.changeGenealogyNameById(genealogyId, newName);
				genealogyNeo4jRepository.changeGenealogyNameById(genealogyId, newName);

				return true;
			}
			else {
				return false;
			}
		}
		catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new MySQLException("修改图谱名称异常。");
		}
	}

	// 修改图谱描述
	@Override
	public Boolean changeGenealogyDescription(String genealogyName, String description) throws MyCypherException, MySQLException {
		try {
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			if(genealogyId != null) {
				genealogyMySQLRepository.changeGenealogyDescription(genealogyId, description);
				return true;
			}
			else {
				return false;
			}
		}
		catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new MySQLException("修改图谱描述异常。");
		}
	}

	// 设置默认中心节点
	@Override
	public Boolean setDefaultCenterPerson(String genealogyName, String personName) throws MySQLException {
		// 验证中心节点的存在性
		// 更改中心节点
		try {
			Person person = personNeo4jRepository.findPersonByName(genealogyName, personName);
			if(person != null) {
				Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
				genealogyMySQLRepository.changeDefaultCenterPerson(genealogyId, personName);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("设置默认中心节点异常。");
		}
	}

	// 增设管理员
	@Override
	public Boolean setAdmin(String genealogyName, String newAdminNickname) throws MySQLException {
		// 在图数据库钟更改admin
		// 在关系数据库中更改
		try {
			// 查询管理员数量（最多三个）
			String admins = genealogyMySQLRepository.findGenealogyAdminByName(genealogyName);
			String[] adminsList = admins.split(" ");
			int adminsListLength = adminsList.length;
			if(adminsListLength <= 2 && adminsListLength >= 0) {
				// 验证该管理是否存在
				for (int i = 0; i < adminsListLength; i++) {
					if(adminsList[i].equals(genealogyName)) {
						return false;
					}
				}
				Long userId = userMySQLRepository.findUserIdByNickname(newAdminNickname);
				Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
				genealogyNeo4jRepository.setNewAdmin(genealogyId, userId);
				// 更改genealogy表中admin
				String genealogyAdmin = admins + " " + newAdminNickname;
				genealogyMySQLRepository.changeGenealogyAdmin(genealogyId, genealogyAdmin);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("增设管理员异常");
		}
	}

	// 转让管理员
	@Override
	public Boolean transferAdmin(String genealogyName, String oldAdminNickname, String newAdminNickname) throws MySQLException {
		// 转交管理身份
		try {
			// 验证该管理是否存在
			String oldGenealogyAdmin = genealogyMySQLRepository.findGenealogyAdminByName(genealogyName);
			String[] adminsList = oldGenealogyAdmin.split(" ");
			int adminsListLength = adminsList.length;
			for (int i = 0; i < adminsListLength; i++) {
				if(adminsList[i].equals(newAdminNickname)) {
					return false;
				}
			}
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Long oldAdminId = userMySQLRepository.findUserIdByNickname(oldAdminNickname);
			Long newAdminId = userMySQLRepository.findUserIdByNickname(newAdminNickname);
			// 更新Neo4j中数据
			genealogyNeo4jRepository.transferAdmin(genealogyId, oldAdminId, newAdminId);
			// 更新MySQL中的信息
			StringBuilder newGenealogyAdmin = new StringBuilder();
			for(int i = 0; i < adminsListLength; i++) {
				// 替换原管理员姓名
				if(adminsList[i].equals(oldAdminNickname)) {
					adminsList[i] = newAdminNickname;
				}
				if(i != adminsListLength - 1) {
					newGenealogyAdmin.append(adminsList[i] + " ");
				}
				else {
					newGenealogyAdmin.append(adminsList[i]);
				}
			}
			String finalGenealogyAdmin = newGenealogyAdmin.toString();
			genealogyMySQLRepository.changeGenealogyAdmin(genealogyId, finalGenealogyAdmin);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("转让管理员身份异常。");
		}
	}

	// 更改默认中心节点
	@Override
	public void changeDefaultCenterPerson(String genealogyName, String newCenterPerson) throws MySQLException {
		try {
			Person person = personNeo4jRepository.findPersonByName(genealogyName, newCenterPerson);
			if(person != null) {
				Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
				genealogyMySQLRepository.changeDefaultCenterPerson(genealogyId,newCenterPerson);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("更改默认中心节点异常。");
		}
	}

	// 通过用户对图谱的关注申请
	@Override
	public void passApplication(String genealogyName, String userNickname) throws MySQLException {
		try {
			// 在neo4j中将关注关系创建
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Long userId = userMySQLRepository.findUserIdByNickname(userNickname);
			userNeo4jRepository.passFocusOnGenealogy(genealogyId, userId);
			// 删除focusApplication表中对应行
			genealogyFocusApplicationMySQLRepository.deleteApplication(genealogyId, userId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("通过用户对图谱的关注申请异常。");
		}
	}

	// 拒绝用户对图谱的关注申请
	@Override
	public void refuseApplication(String genealogyName, String userNickname) throws MySQLException {
		try {
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Long userId = userMySQLRepository.findUserIdByNickname(userNickname);
			// 删除focusApplication表中对应行
			userNeo4jRepository.refuseFocusOnGenealogy(genealogyId, userId);
			genealogyFocusApplicationMySQLRepository.deleteApplication(genealogyId, userId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("通过用户对图谱的关注申请异常。");
		}
	}

	// 取消用户对图谱的关注
	@Override
	public void cancelGenealogyFocus(String genealogyName, String userNickname) throws MySQLException {
		try {
			// 删除关注关系
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Long userId = userMySQLRepository.findUserIdByNickname(userNickname);
			userNeo4jRepository.cancelFocusOnGenealogy(genealogyId, userId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MySQLException("取消用户对图谱的关注异常。");
		}
	}

	// 创建新图谱
	@Override
	public void createNewGenealogy(Long userId, String newGenealogyName, String description, PersonVO person) throws MySQLException {
		try {
			String userNickname = userMySQLRepository.findUserNicknameById(userId);
			// 在MySQL中创建图谱
			genealogyMySQLRepository.createGenealogy(newGenealogyName, userNickname, person.getName(), description);
			// 在Neo4j中创建图谱并创建用户对该图谱的管理关系
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(newGenealogyName);
			genealogyNeo4jRepository.createGenealogy(newGenealogyName, genealogyId, userId);
			// 创建默认中心节点
			personNeo4jRepository.createPerson(newGenealogyName, person.getName(), person.getGender(), person.getDeathTime(),
					person.getBirthTime(), person.getMajorAchievements());
			// 更新动态
			Timestamp time = UpdateRecordUtil.getNowTimestamp();
			String remark = UpdateRecordUtil.createUpdateRemark(newGenealogyName, userNickname, time);
			genealogyUpdateRecordMySQLRepository.createUpdateRecord(genealogyId, "图谱被创建。",
					time, remark);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw  new MySQLException("创建新图谱出现异常。");
		}
	}

}
