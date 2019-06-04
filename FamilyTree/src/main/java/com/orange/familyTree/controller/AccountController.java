package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.dao.AccountCrudRepository;
import com.orange.familyTree.entity.Account;

@RestController
public class AccountController {
	
	@Autowired
	private AccountCrudRepository accountCrudRepository;
	
	@GetMapping(value="/{nickName}")
	@ResponseBody
	public Account findAccountByName(@PathVariable("nickName") String nickName) {
		return accountCrudRepository.findByNickName(nickName);
	}

}
