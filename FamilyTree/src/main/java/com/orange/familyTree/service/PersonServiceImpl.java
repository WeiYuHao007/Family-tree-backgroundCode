package com.orange.familyTree.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.orange.familyTree.dao.mysql.GenealogyUpdateRecordMySQLRepository;
import com.orange.familyTree.pojo.util.UpdateRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.dao.mysql.GenealogyMySQLRepository;
import com.orange.familyTree.dao.neo4j.PersonNeo4jRepository;
import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.PersonVO;
import com.orange.familyTree.pojo.specialPojo.NodeShowVO;
import com.orange.familyTree.pojo.specialPojo.RelationshipVO;
import com.orange.familyTree.pojo.util.NodeUtil;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;


@Service
@Transactional
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonNeo4jRepository personNeo4jRepository;

	@Autowired
	private GenealogyUpdateRecordMySQLRepository genealogyUpdateRecordMySQLRepository;

	@Autowired
	private GenealogyMySQLRepository genealogyMySQLRepository;

	@Override
	public Person getPerson(String genealogyName, String personName) {
		try {
			Person person = personNeo4jRepository.findPersonByName(genealogyName, personName);
			if (person != null) {
				return person;
			} else {
				throw new MyCypherException("节点信息查询失败,请检查节点是否存在。");
			}
		}
		catch(MyCypherException mex) {
			mex.printStackTrace();
			throw new MyCypherException(mex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("节点信息查询异常。");
		}
	}

	@Override
	public Boolean findPersonWhetherExist(String genealogyName, String personName) {
		try {
			String name = personNeo4jRepository.findPersonWhetherExist(genealogyName, personName);
			System.out.println(name);
			if(name != null) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("节点存在性查询异常。");
		}
	}

	// 查询主要图谱数据
	@Override
	public Result getCircleMainPersonData(String genealogyName, String centerNodeName, Integer radius) throws MyCypherException{
		try{
			String startPersonName = personNeo4jRepository.findForefathers(genealogyName, centerNodeName);
			if(startPersonName == null) {
				startPersonName = centerNodeName;
			}
			NodeShowVO startNodeShowVO = new NodeShowVO(startPersonName, 0, 0);
			// 渲染节点列表
			ArrayList<NodeShowVO> nodeShowVOs = new ArrayList<>();
			nodeShowVOs.add(startNodeShowVO);
			// 关系列表
			ArrayList<RelationshipVO> relationshipVOs = new ArrayList<>();
			// 中心节点列表
			ArrayList<NodeShowVO> centerNodes = new ArrayList<>();
			centerNodes.add(startNodeShowVO);

			int iterationsNum = 6;

			// 填充渲染节点列表(迭代中心节点)
			while(!centerNodes.isEmpty() && iterationsNum != 0) {
				int centerNodesLength = centerNodes.size();
				ArrayList<NodeShowVO> tempCenterNodes = new ArrayList<>();

				for(int i = 0; i < centerNodesLength; i++) {
					// 中心节点数据
					String tempCenterNodeName = centerNodes.get(i).getName();
					Integer tempCenterNodeX = centerNodes.get(i).getX();
					Integer tempCenterNodeY = centerNodes.get(i).getY();

					// 查询该节点的儿子节点
					List<String> sonsNameList = personNeo4jRepository.findSons(genealogyName,
							centerNodes.get(i).getName());
					// 包装儿子节点
					int sonsNameListLength = sonsNameList.size();
					if (sonsNameListLength != 0) {
						// 将查询到的节点包装好打包成节点数组
						ArrayList<NodeShowVO> sonsNodes = NodeUtil.addSonsNodes(sonsNameList,
								tempCenterNodeName, tempCenterNodeX, tempCenterNodeY, radius);

						int sonsNodesLength = sonsNodes.size();
						for(int j = 0; j < sonsNodesLength; j++) {
							// 将包装好的数组添加到渲染节点列表
							nodeShowVOs.add(sonsNodes.get(j));
							// 将包装好的数组添加到临时中心节点列表
							tempCenterNodes.add(sonsNodes.get(j));
						}
					}

					// 查询该节点的女儿与妻子
					List<String> wifeAndDaughterNameList = personNeo4jRepository.findWifeAndDaughter(genealogyName,
							tempCenterNodeName);
					// 包装妻子与女儿节点
					int wifeAndDaughterNameListLength = wifeAndDaughterNameList.size();
					if (wifeAndDaughterNameListLength != 0) {
						// 将查询到的节点包装好打包成节点数组
						ArrayList<NodeShowVO> wifeAndDaughterNodes = NodeUtil.addNotSonsNodes(wifeAndDaughterNameList,
								tempCenterNodeName, tempCenterNodeX, tempCenterNodeY, radius);
						int wifeAndDaughterNodesLength = wifeAndDaughterNodes.size();
						// 将包装好的数组添加到渲染节点列表
						for(int j = 0; j < wifeAndDaughterNodesLength; j++) {
							nodeShowVOs.add(wifeAndDaughterNodes.get(j));
						}
					}
				}
				// 替换中心节点数组
				centerNodes = tempCenterNodes;
				// 更新半径
				radius *= 2;
				// 迭代次数减一
				iterationsNum--;

			}

			// 填充关系列表
			int nodeShowVOsLength = nodeShowVOs.size();
			for(int i = 0; i < nodeShowVOsLength; i++) {
				for (int j = 0; j < nodeShowVOsLength && i != j; j++) {
					String positiveRelationshipName = personNeo4jRepository.findRelationship(genealogyName,
							nodeShowVOs.get(i).getName(), nodeShowVOs.get(j).getName());
					if(positiveRelationshipName != null) {
						RelationshipVO positiveRelationshipVO = new RelationshipVO(nodeShowVOs.get(i).getName(),
								nodeShowVOs.get(j).getName(), positiveRelationshipName);
						relationshipVOs.add(positiveRelationshipVO);
					}
					String reverseRelationshipName = personNeo4jRepository.findRelationship(genealogyName,
							nodeShowVOs.get(j).getName(), nodeShowVOs.get(i).getName());
					if(reverseRelationshipName != null) {
						RelationshipVO reverseRelationshipVO = new RelationshipVO(nodeShowVOs.get(j).getName(),
								nodeShowVOs.get(i).getName(), reverseRelationshipName);
						relationshipVOs.add(reverseRelationshipVO);
					}
				}
			}
			Object[] data = new Object[] {nodeShowVOs, relationshipVOs, centerNodes};
			return ResultFactory.buildSuccessResult(data);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("获取图谱主要显示数据失败");
		}
	}

	@Override
	public Result getHashMainPersonData(String genealogyName, Integer groupNum) {
		try{
			// 获取信息
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			// 限制每次10个节点
			List<String> personNameList = personNeo4jRepository.findPersonByGroup(genealogyId, groupNum * 10);
			// 开始的index
			Integer startIndex = 10 * (groupNum - 1);
			// 包装节点
			ArrayList<NodeShowVO> nodeShowVOs = NodeUtil.addHashNodes(personNameList, 0.0, 0.0,
					800.0 * 25, 500.0 * 25);
			// 关系列表
			ArrayList<RelationshipVO> relationshipVOs = new ArrayList<>();
			int nodeShowVOsLength = nodeShowVOs.size();
			if(nodeShowVOsLength - 1 < startIndex) {
				return ResultFactory.buildFailResult("已经加载完全。");
			}
			for(int i = 0; i < nodeShowVOsLength; i++) {
				for (int j = startIndex; j < nodeShowVOsLength && i != j; j++) {
					String positiveRelationshipName = personNeo4jRepository.findRelationship(genealogyName,
							nodeShowVOs.get(i).getName(), nodeShowVOs.get(j).getName());
					if(positiveRelationshipName != null) {
						RelationshipVO positiveRelationshipVO = new RelationshipVO(nodeShowVOs.get(i).getName(),
								nodeShowVOs.get(j).getName(), positiveRelationshipName);
						relationshipVOs.add(positiveRelationshipVO);
					}
					String reverseRelationshipName = personNeo4jRepository.findRelationship(genealogyName,
							nodeShowVOs.get(j).getName(), nodeShowVOs.get(i).getName());
					if(reverseRelationshipName != null) {
						RelationshipVO reverseRelationshipVO = new RelationshipVO(nodeShowVOs.get(j).getName(),
								nodeShowVOs.get(i).getName(), reverseRelationshipName);
						relationshipVOs.add(reverseRelationshipVO);
					}
				}
			}
			Object[] data = new Object[] {nodeShowVOs.subList(startIndex, personNameList.size()), relationshipVOs};
			return ResultFactory.buildSuccessResult(data);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("获取主要人物数据异常。");
		}
	}

	@Override
	public Result getShortPath(String genealogyName, String startPersonName, String endPersonName, Integer radius) {
		// 查找两个节点间的最短路径
		List<String> nodesNameList = personNeo4jRepository.findShortPathNodes(genealogyName, startPersonName, endPersonName);
		if(nodesNameList.size() == 0) {
			throw new MyCypherException("路径不存在。");
		}
		// 将查询到的名称转化为具有x,y位置的节点并打包成数组
		ArrayList<NodeShowVO> nodes = NodeUtil.addNotSonsNodes(nodesNameList, "", 0, 0, radius);
		
		// 查询相邻节点间的关系,并打包成数组
		ArrayList<RelationshipVO> relationships = new ArrayList<>();
		for(int i = 1; i < nodesNameList.size(); i++) {
			String relationshipName = personNeo4jRepository.findRelationship(genealogyName,
					nodesNameList.get(i-1),nodesNameList.get(i));
			RelationshipVO relationshipVO = new RelationshipVO(nodesNameList.get(i-1), nodesNameList.get(i),
					relationshipName);
			relationships.add(relationshipVO);
		}
		
		Object[] data = new Object[] {nodes, relationships};
		return ResultFactory.buildSuccessResult(data);

	}

	@Override
	public Result createPerson(String genealogyName, PersonVO personVO, String adminNickname) {
		try {
			personNeo4jRepository.createPerson(genealogyName,  personVO.getName(), personVO.getGender(), personVO.getDeathTime(),
					personVO.getBirthTime(), personVO.getMajorAchievements());
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Timestamp time = UpdateRecordUtil.getNowTimestamp();
			String remark = UpdateRecordUtil.createUpdateRemark(genealogyName, adminNickname, time);
			genealogyUpdateRecordMySQLRepository.createUpdateRecord(genealogyId, "添加了" +
							personVO.getName() + "的信息。",time, remark);
			return ResultFactory.buildSuccessResult("节点创建成功。");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("创建节点失败。");
		}
	}

	@Override
	public Result deletePerson(String genealogyName, String personName, String adminNickname) {
		try {
			personNeo4jRepository.deletePerson(genealogyName, personName);
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			Timestamp time = UpdateRecordUtil.getNowTimestamp();
			String remark = UpdateRecordUtil.createUpdateRemark(genealogyName, adminNickname, time);
			genealogyUpdateRecordMySQLRepository.createUpdateRecord(genealogyId, "删除了" +
					personName + "的信息。",time, remark);

			return ResultFactory.buildSuccessResult("节点删除成功。");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("节点删除失败。");
		}
	}

	@Override
	public Result createRelationship(String genealogyName, RelationshipVO relationshipVO, String adminNickname) {
		try {
			// 不存在的人查出来的关系也不存在
			String existRelationshipName = personNeo4jRepository.findRelationship(genealogyName, relationshipVO.getSource(), 
					relationshipVO.getTarget());
			if (existRelationshipName == null || existRelationshipName == "") {
				String relationshipName = relationshipVO.getRelationshipName();
				if (relationshipName.equals("父亲")) {
					personNeo4jRepository.createFartherRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
					
				}
				if (relationshipName.equals("母亲")) {
					personNeo4jRepository.createMotherRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
					
				}
				if (relationshipName.equals("儿子")) {
					personNeo4jRepository.createSonRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
				}
				if (relationshipName.equals("女儿")) {
					personNeo4jRepository.createDaughterRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
				}
				if (relationshipName.equals("兄弟")) {
					personNeo4jRepository.createBrotherRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
				}
				if (relationshipName.equals("丈夫")) {
					personNeo4jRepository.createManToWifeRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
				}
				if (relationshipName.equals("妻子")) {
					personNeo4jRepository.createManToWifeRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
				}
				Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
				Timestamp time = UpdateRecordUtil.getNowTimestamp();
				String remark = UpdateRecordUtil.createUpdateRemark(genealogyName, adminNickname, time);
				genealogyUpdateRecordMySQLRepository.createUpdateRecord(genealogyId, "完善了" +
						relationshipVO.getSource() + "与" + relationshipVO.getTarget() + "之间的关系。",time, remark);
				return ResultFactory.buildSuccessResult("关系创建成功。");
			}
			else {
				throw new MyCypherException("关系已存在，请删除后再添加。");
			}
		}
		catch (MyCypherException myEx) {
			throw new MyCypherException(myEx.getMessage());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("关系创建失败。");
		}
	}

	@Override
	public Result changePersonInfo(String genealogyName, PersonVO personVO, String adminNickname) throws MyCypherException{
		try {
			personNeo4jRepository.changePersonInfo(genealogyName, personVO.getName(), personVO.getGender(), personVO.getBirthTime(),
					personVO.getDeathTime(), personVO.getMajorAchievements());
			Timestamp time = UpdateRecordUtil.getNowTimestamp();
			String remark = UpdateRecordUtil.createUpdateRemark(genealogyName, adminNickname, time);
			Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
			String commit = personVO.getCommit();
			if(commit == "" || commit == null) {
				commit = "修改了" + personVO.getName() + "的信息。";
			}
			genealogyUpdateRecordMySQLRepository.createUpdateRecord(genealogyId, commit, time, remark);
			return ResultFactory.buildSuccessResult("修改成功。");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("修改失败。");
		}
	}

	@Override
	public Result deleteRelationship(String genealogyName, String sourceName, String targetName, String adminNickname) {
		try {
			String existRelationshipName = personNeo4jRepository.findRelationship(genealogyName, 
					sourceName, targetName);
			if (existRelationshipName != null) {
				personNeo4jRepository.deleteRelationship(genealogyName, sourceName, targetName);
				Long genealogyId = genealogyMySQLRepository.findGenealogyIdByName(genealogyName);
				Timestamp time = UpdateRecordUtil.getNowTimestamp();
				String remark = UpdateRecordUtil.createUpdateRemark(genealogyName, adminNickname, time);
				genealogyUpdateRecordMySQLRepository.createUpdateRecord(genealogyId, "删除了" +
						sourceName + "与" + targetName + "之间的关系。",time, remark);
				return ResultFactory.buildSuccessResult("关系删除成功。");
			}
			else {
				return ResultFactory.buildFailResult("关系不存在，无法删除。");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("关系删除失败。");
		}
	}

}
