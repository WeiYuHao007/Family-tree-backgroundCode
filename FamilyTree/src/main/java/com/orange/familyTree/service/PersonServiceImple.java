package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.dao.PersonCrudRepository;
import com.orange.familyTree.entity.Person;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.GenealogyDO;
import com.orange.familyTree.pojo.NodeVO;
import com.orange.familyTree.pojo.RelationshipVO;
import com.orange.familyTree.pojo.util.NodeUtil;
import com.orange.familyTree.pojo.util.Result;
import com.orange.familyTree.pojo.util.ResultFactory;


@Service
@Transactional
public class PersonServiceImple implements PersonService{
	
	@Autowired
	private PersonCrudRepository personCrudRepository;


	@Override
	public Person getPerson(String genealogyName, String personName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Result getWifesAndDaughters(String genealogyName, NodeVO nodeVO, Integer radius) {
		try {
			List<String> nodesNameList = personCrudRepository.findWifeAndDaughter(genealogyName, 
					nodeVO.getName());
			int nodesNameListLength = nodesNameList.size();
			if(nodesNameListLength != 0) {
				// 将查询到的节点包装好打包成节点数组
				ArrayList<NodeVO> nodes = NodeUtil.addNotSonsNodes(nodesNameList, nodeVO, radius);
				
				// 查询各个节点间的关系,并打包成关系数组
				ArrayList<RelationshipVO> relationships = new ArrayList<>();
				
				// 将中心节点加入名称数组
				nodesNameList.add(0, nodeVO.getName());
				nodesNameListLength++;
				
				for(int i = 0; i < nodesNameListLength - 1; i++) {
					for(int j = i + 1; j < nodesNameListLength; j++) {
						String relationshipName = personCrudRepository.findRelationship(genealogyName, 
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
			System.out.print(ex.getStackTrace());
			return null;
		}
	}

	@Override
	public Result getSons(String genealogyName, NodeVO fartherNode, Integer radius) {
		try {
			List<String> sonsNameList = personCrudRepository.findSons(genealogyName, 
					fartherNode.getName());
			int sonsNameListLength = sonsNameList.size();
			if(sonsNameListLength != 0) {
				// 将查询到的节点包装好打包成节点数组
				ArrayList<NodeVO> nodes = NodeUtil.addSonsNodes(sonsNameList, fartherNode, radius);
				// 将节点间的关系包装好并打包成关系数组
				ArrayList<RelationshipVO> relationships = new ArrayList<>();
				for(int i = 0; i < sonsNameList.size(); i++) {
					String relationshipName = "儿子";
					RelationshipVO relationshipVO = new RelationshipVO(sonsNameList.get(i), fartherNode.getName(),
							relationshipName);
					relationships.add(relationshipVO);
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
			System.out.print(ex.getStackTrace());
			return null;
		}
	}

	@Override
	public Result findShortPath(String genealogyName, String startPersonName, String endPersonName, Integer radius) {
		// 查找两个节点间的最短路径
		List<String> nodesNameList = personCrudRepository.findShortPathNodes(genealogyName, startPersonName, endPersonName);
		if(nodesNameList.size() == 0) {
			throw new MyCypherException("路径不存在。");
		}
		
		// 创建一个虚拟的中心节点，其他节点围绕着这个中心节点设置（x, y）坐标
		NodeVO node  = new NodeVO("template", 0, 0);
		// 将查询到的名称转化为具有x,y位置的节点并打包成数组
		ArrayList<NodeVO> nodes = NodeUtil.addNotSonsNodes(nodesNameList, node, radius);
		
		// 查询相邻节点间的关系,并打包成数组
		ArrayList<RelationshipVO> relationships = new ArrayList<>();
		for(int i = 1; i < nodesNameList.size(); i++) {
			String relationshipName = personCrudRepository.findRelationship(genealogyName, 
					nodesNameList.get(i-1),nodesNameList.get(i));
			RelationshipVO relationshipVO = new RelationshipVO(nodesNameList.get(i-1), nodesNameList.get(i),
					relationshipName);
			relationships.add(relationshipVO);
		}
		
		Object[] data = new Object[] {nodes, relationships};
		return ResultFactory.buildSuccessResult(data);

	}

	@Override
	public void modifyPersonProperties(GenealogyDO newGenealogyDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPerson(GenealogyDO genealogyDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPersonRelationship(String startPersonName, String endPersonName, String relationshipName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonRelationship(String startPersonName, String endPersonName, String relationshipName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(String personName) {
		// TODO Auto-generated method stub
		
	}

}
