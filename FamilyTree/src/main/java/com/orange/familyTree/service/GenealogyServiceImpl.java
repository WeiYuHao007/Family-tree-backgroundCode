package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.familyTree.dao.neo4j.PersonNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.dao.neo4j.GenealogyNeo4jRepository;
import com.orange.familyTree.entity.neo4j.Genealogy;
import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.exceptions.MyCypherException;


@Service
@Transactional
public class GenealogyServiceImpl implements GenealogyService {
	
	@Autowired
	private GenealogyNeo4jRepository genealogyNeo4jRepository;

	@Override
	public Genealogy findGenealogies() throws MyCypherException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws MyCypherException {
		// TODO Auto-generated method stub
		
	}


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

	@Override
	public List<String> findPersonsByGenealogyName(String genealogyName) {
		try {
			List<String> nameList = genealogyNeo4jRepository.findPersonsByGenealogyName(genealogyName);
			return nameList;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new MyCypherException("指定图谱所拥有的节点姓名查询异常。");
		}
	}


	@Override
	public List<Long> findGenealogyFollowersByName(String genealogyName) throws MyCypherException {
		try {
			List<Long> followersList = genealogyNeo4jRepository.findGenealogyFollowersByName(genealogyName);
			return followersList;
		}
		catch(Exception ex) {
			throw new MyCypherException("查询图谱关注者异常。");
		}
	}



	
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


}
