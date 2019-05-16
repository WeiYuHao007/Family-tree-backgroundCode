package com.orange.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.application.dao.Peoplerepository;
import com.orange.application.dao.Userrepository;
import com.orange.application.entity.People;
import com.orange.application.entity.Account;

@Service
public class AllServiceImpl implements AllService{
	@Autowired
	private Peoplerepository peoplerepository = null;
	@Autowired
	private Userrepository userrepository = null;
	
	@Override
	public List<People> findAll() {
		return peoplerepository.findAll();
	}

	@Override
	public Account getUserByName(String username) {
		return userrepository.getUserByName(username);
	}

	@Override
	public List<String> findRolesByUserName(String username) {
		return userrepository.findRolesByUserName(username);
	}
}