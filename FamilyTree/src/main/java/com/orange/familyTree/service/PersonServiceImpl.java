package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public Person getPerson(String genealogyName, String personName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Result getWivesAndDaughters(String genealogyName, String personName, Integer x, Integer y, 
			Integer radius) {
		try {
			List<String> nodesNameList = personNeo4jRepository.findWifeAndDaughter(genealogyName,
					personName);
			int nodesNameListLength = nodesNameList.size();
			if(nodesNameListLength != 0) {
				// 将查询到的节点包装好打包成节点数组
				ArrayList<NodeShowVO> nodes = NodeUtil.addNotSonsNodes(nodesNameList, personName, x,
						y, radius);
				
				// 查询各个节点间的关系,并打包成关系数组
				ArrayList<RelationshipVO> relationships = new ArrayList<>();
				
				// 将中心节点加入名称数组
				nodesNameList.add(0, personName);
				nodesNameListLength++;
				
				for(int i = 0; i < nodesNameListLength; i++) {
					for (int j = 0; j < nodesNameListLength && i != j; j++) {
						String relationshipName = personNeo4jRepository.findRelationship(genealogyName,
								nodesNameList.get(i), nodesNameList.get(j));
						RelationshipVO relationshipVO = new RelationshipVO(nodesNameList.get(i), nodesNameList.get(j),
								relationshipName);
						relationships.add(relationshipVO);
					}
				}

				for(int i = nodesNameListLength - 1; i >= 0; i--) {
					for(int j = nodesNameListLength - 1; j >= 0 && i != j; j--) {
						String relationshipName = personNeo4jRepository.findRelationship(genealogyName,
								nodesNameList.get(i),nodesNameList.get(j));
						RelationshipVO relationshipVO = new RelationshipVO(nodesNameList.get(i), nodesNameList.get(j),
								relationshipName);
						relationships.add(relationshipVO);
					}
				}

				Object[] data = new Object[] {nodes, relationships};
				return ResultFactory.buildSuccessResult(data);
			}
			else {
				// 没有妻子或女儿节点，故不返回任何数据
				return null;
			}
		}
		catch(Exception ex) {
			// 无需返回异常，将异常压制，不向前端返回任何信息
			return null;
		}
	}

	@Override
	public Result getSons(String genealogyName, String fartherName, Integer x,Integer y, Integer radius) {
		try {
			List<String> sonsNameList = personNeo4jRepository.findSons(genealogyName, fartherName);
			int sonsNameListLength = sonsNameList.size();
			if(sonsNameListLength != 0) {
				// 将查询到的节点包装好打包成节点数组
				ArrayList<NodeShowVO> nodes = NodeUtil.addSonsNodes(sonsNameList, fartherName, x, y, radius);
				// 将节点间的关系包装好并打包成关系数组
				ArrayList<RelationshipVO> relationships = new ArrayList<>();
				for(int i = 0; i < sonsNameList.size(); i++) {
					String sons = "儿子";
					RelationshipVO sonRelationship = new RelationshipVO(sonsNameList.get(i), fartherName,
							sons);
					relationships.add(sonRelationship);
					String farther = "父亲";
					RelationshipVO fartherRelationship = new RelationshipVO(fartherName, 
							sonsNameList.get(i), farther);
					relationships.add(fartherRelationship);
				}
				// 成功返回
				Object[] data = new Object[] {nodes, relationships};
				return ResultFactory.buildSuccessResult(data);
			}
			else {
				// 查询无结果此节点没有儿子，不返回任何数据
				return ResultFactory.buildSuccessResult("该节点无已知的子节点。");
			}
		}
		catch(Exception ex) {
			// 无需返回异常，将异常压制，不向前端返回任何信息
			return null;
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
	public Result createPerson(String genealogyName, PersonVO personVO) {
		try {
			personNeo4jRepository.createPerson(genealogyName,  personVO.getName(), personVO.getDeathTime(), 
					personVO.getBirthTime(), personVO.getMajorAchievements());
			return ResultFactory.buildSuccessResult("节点创建成功。");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("创建节点失败。");
		}
	}

	@Override
	public Result deletePerson(String genealogyName, String personName) {
		try {
			personNeo4jRepository.deletePerson(genealogyName, personName);
			return ResultFactory.buildSuccessResult("节点删除成功。");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("节点删除失败。");
		}
	}

	@Override
	public Result createRelationship(String genealogyName, RelationshipVO relationshipVO) {
		try {
			String existRelationshipName = personNeo4jRepository.findRelationship(genealogyName, relationshipVO.getSource(), 
					relationshipVO.getTarget());
			if (existRelationshipName == null || existRelationshipName == "") {
				String relationshipName = relationshipVO.getRelationshipName();
				if (relationshipName == "父亲") {
					personNeo4jRepository.createSonRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
					personNeo4jRepository.createFartherRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
					
				}
				if (relationshipName == "母亲") {
					personNeo4jRepository.createSonRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
					personNeo4jRepository.createMotherRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
					
				}
				if (relationshipName == "儿子") {
					personNeo4jRepository.createSonRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
					personNeo4jRepository.createFartherRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
				}
				if (relationshipName == "女儿") {
					personNeo4jRepository.createDaughterRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
					personNeo4jRepository.createFartherRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
				}
				if (relationshipName == "兄弟") {
					personNeo4jRepository.createBrotherRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
				}
				if (relationshipName == "丈夫") {
					personNeo4jRepository.createManToWifeRelationship(genealogyName, 
							relationshipVO.getSource(), relationshipVO.getTarget());
				}
				if (relationshipName == "妻子") {
					personNeo4jRepository.createManToWifeRelationship(genealogyName, 
							relationshipVO.getTarget(), relationshipVO.getSource());
				}
				return ResultFactory.buildSuccessResult("关系创建成功。");
			}
			else {
				throw new MyCypherException("关系已存在，请删除后再添加。");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("关系创建失败。");
		}
	}

	@Override
	public Result deleteRelationship(String genealogyName, String sourceName, String targetName) {
		try {
			String existRelationshipName = personNeo4jRepository.findRelationship(genealogyName, 
					sourceName, targetName);
			if (existRelationshipName == null || existRelationshipName == "") {
				personNeo4jRepository.deleteRelationship(genealogyName, sourceName, targetName);
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
