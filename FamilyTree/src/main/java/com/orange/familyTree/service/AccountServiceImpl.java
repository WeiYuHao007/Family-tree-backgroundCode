package com.orange.familyTree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.exceptions.CypherException;
import com.orange.familyTree.pojo.AccountDetail;
import com.orange.familyTree.pojo.AccountViewDetail;
import com.orange.familyTree.repository.AccountCrudRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountCrudRepository accountCrudRepository;
	
	@Override
	public AccountDetail findAccount(AccountViewDetail accountViewDetail) throws CypherException{
		// 登入账号
		try {
			if(accountViewDetail.getTelephoneNumber() != null) {
				// 通过电话号码登入
				Account account = accountCrudRepository.findByTelephoneNumberAndPassword(accountViewDetail.getTelephoneNumber(), 
						accountViewDetail.getPassword());
				AccountDetail myAccountDetail = AccountDetail.changeAToAD(account);
				return myAccountDetail;
			}
			else {
				Account account = accountCrudRepository.findByEmailAndPassword(accountViewDetail.getEmail(), 
						accountViewDetail.getPassword());
				AccountDetail myAccountDetail = AccountDetail.changeAToAD(account);
				return myAccountDetail;
			}
		}
		catch(Exception ex){
			// 账号登入失败
			throw new CypherException("账号或密码错误，请重新输入。");
		}
	}

	@Override
	public void registerAccount(AccountDetail accountDetail) throws CypherException {
		// 注册账号
		try {
			accountCrudRepository.registerAccount(accountDetail.getTelephoneNumber(), 
					accountDetail.getEmail(), accountDetail.getNickName(), accountDetail.getPassword(), 
					accountDetail.getPrivilegeRole(), accountDetail.getRegistrationTime());
		}
		catch(Exception cex) {
			throw new CypherException("注册失败");
		}
		
	}


	@Override
	public void focusOnGenealogy(String accountName, String genealogyName) throws CypherException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelFocusOnGenealogy(String accountName, String genealogyName) throws CypherException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Genealogy> viewAllFocusOnGenealogy(String accountName) throws CypherException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String accountName, String password) throws CypherException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(AccountViewDetail accountViewDetail) throws CypherException {
		// TODO Auto-generated method stub
		
	}

}
