package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.entity.Person;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.GenealogyDO;

public interface GenealogyService {
	
	//查询目标族谱的关注者
	List<Integer> findFollowersByGenealogy(String genealogyName) throws MyCypherException;
	
	//查询用户关注的图谱
	List<String> findAllGenealogy(Integer phoneNum) throws MyCypherException;
	
	//查询指定名称的图谱
	List<Genealogy> findGenealogies() throws MyCypherException;
	
	//创建图谱
	void createGenealogy(GenealogyDO genealogyDetail) throws MyCypherException;
	
	//修改图谱名称
	void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws MyCypherException;
	
	//展开图谱
	List<Person> showGenealogy() throws MyCypherException;
}
