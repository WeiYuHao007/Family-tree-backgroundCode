package com.orange.familyTree.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.dao.PersonCrudRepository;
import com.orange.familyTree.entity.Person;
import com.orange.familyTree.exceptions.MyCypherException;
import com.orange.familyTree.pojo.GenealogyDO;
import com.orange.familyTree.pojo.RelationshipVO;
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
	public Result findShortPath(String genealogyName, String startPersonName, String endPersonName) {
		try {
			List<String> names = personCrudRepository.findShortPathNodes(genealogyName, startPersonName, endPersonName);
			if(names == null) {
				throw new MyCypherException("路径不存在。");
			}
			System.out.println(names.size());
			ArrayList<RelationshipVO> relationships = new ArrayList<>();
			for(int i = 1; i < names.size(); i++) {
				String relationshipName = personCrudRepository.findRelationship(names.get(i-1), 
						names.get(i));
				RelationshipVO relationship = new RelationshipVO(names.get(i-1), names.get(i), relationshipName);
				relationships.add(relationship);
			}
			Object[] data = new Object[] {names, relationships};
			return ResultFactory.buildSuccessResult(data);
		}
		catch(Exception ex) {
			throw new MyCypherException("最短路径查询出现异常！");
		}
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
