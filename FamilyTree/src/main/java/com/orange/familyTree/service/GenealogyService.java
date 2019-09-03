package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.familyTree.entity.neo4j.Genealogy;
import com.orange.familyTree.entity.neo4j.Person;
import com.orange.familyTree.exceptions.MyCypherException;

public interface GenealogyService {
	
	// 查询目标族谱的管理员
	List<Long> findGenealogyAdminsByName(String genealogyName) throws MyCypherException;
	
	// 查询目标族谱的关注者
	List<Long> findGenealogyFollowersByName(String genealogyName) throws MyCypherException;
	
	// 查询用户关注的图谱
	List<String> findAllGenealogy(Long userId) throws MyCypherException;

	// 查询指定图谱拥有的所有节点名称
	List<String> findPersonsByGenealogyName (String genealogyName);

	// 查询指定名称的图谱
	Genealogy findGenealogies() throws MyCypherException;
	
	// 修改图谱名称
	void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws MyCypherException;
}
