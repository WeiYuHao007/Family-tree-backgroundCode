package com.orange.familyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.dao.GenealogyCrudRepository;
import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.entity.Person;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.GenealogyDO;


@Service
@Transactional
public class GenealogyServiceImpl implements GenealogyService {
	
	@Autowired
	private GenealogyCrudRepository genealogyCrudRepository;

	@Override
	public List<Genealogy> findGenealogies() throws MyCypherException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws MyCypherException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void createGenealogy(GenealogyDO genealogyDetail) throws MyCypherException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Integer> findFollowersByGenealogy(String genealogyName) {
		try {
			List<Integer> followersList = genealogyCrudRepository.findGenealogyFollowers(genealogyName);
			return followersList;
		}
		catch(Exception ex) {
			throw new MyCypherException("关注图谱列表显示失败，请重新认证身份。");
		}
	}


	@Override
	public List<String> findAllGenealogy(Integer phoneNum) throws MyCypherException {
		try {
			List<String> nameList = genealogyCrudRepository.findAllGenealogy(phoneNum);
			return nameList;
		}
		catch(Exception ex){
			throw new MyCypherException("读取关注图谱异常。");
		}
	}


	@Override
	public List<Person> showGenealogy() throws MyCypherException {
		// TODO Auto-generated method stub
		return null;
	}

}
