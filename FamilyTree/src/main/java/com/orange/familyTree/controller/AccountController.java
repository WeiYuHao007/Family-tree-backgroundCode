package com.orange.familyTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.pojo.AccountDetail;
import com.orange.familyTree.service.AccountService;

@CrossOrigin(origins = {"http://localhost:8080","null"})
@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value="/account")
	@ResponseBody
	public AccountDetail logIn(@RequestBody AccountDetail accountDetail) {
		//简单处理，后续丰富逻辑
		Account account = accountService.findAccount(accountDetail.getTelephoneNumber(), 
				accountDetail.getPassword());
		AccountDetail myaccountDetail = AccountDetail.changeToAccountDetail(account);
		if(myaccountDetail != null) {
			return myaccountDetail;
		}
		else {
			return null;
		}
	}

}
