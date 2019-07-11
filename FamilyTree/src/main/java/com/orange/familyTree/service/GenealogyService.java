package com.orange.familyTree.service;

import java.util.List;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.exceptions.CypherException;

public interface GenealogyService {
	
	//查询指定的图谱
	List<Genealogy> findGenealogies() throws CypherException;
	
	//查询指定图谱的超级管理员
	List<Account> findSuperAdmin() throws CypherException;
	
	//查询指定图谱的管理员
	List<Account> findAdmin() throws CypherException;
	
	//创建图谱
	
	
	//修改图谱名称
	void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws CypherException;
	
	//更改超级管理员
	void changeSuperAdmin(String oldSuperAdmin, String newSuperAdmin) throws CypherException;
	
	//增加管理员
	void addAdmin(String Genealogy, String accountName) throws CypherException;
	
	//撤销管理员
	void cancelAdmin(String genealogy, String accountName) throws CypherException;
	
}
