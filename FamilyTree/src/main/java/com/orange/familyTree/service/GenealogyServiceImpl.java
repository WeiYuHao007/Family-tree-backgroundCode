package com.orange.familyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.entity.Person;
import com.orange.familyTree.exceptions.CypherException;
import com.orange.familyTree.pojo.GenealogyDO;
import com.orange.familyTree.repository.GenealogyCrudRepository;


@Service
@Transactional
public class GenealogyServiceImpl implements GenealogyService {
	@Autowired
	private GenealogyCrudRepository genealogyCrudRepository;

	@Override
	public List<Genealogy> findGenealogies() throws CypherException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws CypherException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createGenealogy(GenealogyDO genealogyDetail) throws CypherException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Integer> findFollowersByGenealogy(String genealogyName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> findAllGenealogy(Integer phoneNum) throws CypherException {
		try {
			List<String> nameList = genealogyCrudRepository.findAllGenealogy(phoneNum);
			return nameList;
		}
		catch(Exception ex){
			throw new CypherException("读取关注图谱异常。");
		}
	}


	@Override
	public List<Person> showGenealogy() throws CypherException {
		// TODO Auto-generated method stub
		return null;
	}

}
