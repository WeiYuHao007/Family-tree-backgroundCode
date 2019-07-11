package com.orange.familyTree.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.exceptions.CypherException;


@Service
@Transactional
public class GenealogyServiceImpl implements GenealogyService {

	@Override
	public List<Genealogy> findGenealogies() throws CypherException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findSuperAdmin() throws CypherException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAdmin() throws CypherException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeGenealogyName(String oldGenealogyName, String newGenealogy) throws CypherException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSuperAdmin(String oldSuperAdmin, String newSuperAdmin) throws CypherException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAdmin(String Genealogy, String accountName) throws CypherException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAdmin(String genealogy, String accountName) throws CypherException {
		// TODO Auto-generated method stub
		
	}

}
