package com.orange.familyTree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.repository.AccountCrudRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountCrudRepository accountCrudRepository;
	
	@Override
	public Account findAccount(String telephoneNumber, String password) {
		return accountCrudRepository.findByTelephoneNumberAndPassword(telephoneNumber, password);
	}

}
