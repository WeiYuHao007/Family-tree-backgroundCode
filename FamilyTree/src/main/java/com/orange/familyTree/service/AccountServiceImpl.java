package com.orange.familyTree.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orange.familyTree.entity.Account;
import com.orange.familyTree.entity.Genealogy;
import com.orange.familyTree.exceptions.CypherException;
import com.orange.familyTree.pojo.AccountDO;
import com.orange.familyTree.pojo.LoginVO;
import com.orange.familyTree.repository.AccountCrudRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountCrudRepository accountCrudRepository;
	
	
	@Override
	public AccountDO findAccount(LoginVO loginVO) throws CypherException{
		// 登入账号
		try {
			if(loginVO.getTelephoneNumber() != null) {
				// 通过电话号码登入
				Account account = accountCrudRepository.findByTelephoneNumberAndPassword(loginVO.getTelephoneNumber(), 
						loginVO.getPassword());
				AccountDO accountDetail = AccountDO.changeAToDO(account);
				return accountDetail;
			}
			else {
				Account account = accountCrudRepository.findByEmailAndPassword(loginVO.getEmail(), 
						loginVO.getPassword());
				AccountDO accountDetail = AccountDO.changeAToDO(account);
				return accountDetail;
			}
		}
		catch(Exception ex){
			// 账号登入失败
			throw new CypherException("账号或密码错误，请重新输入。");
		}
	}

	
	@Override
	public void registerAccount(AccountDO accountDetail) throws CypherException {
		// 注册账号
		try {
			//获得当前系统时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			accountDetail.setRegistrationTime(df.format(new Date()));
			accountCrudRepository.registerAccount(accountDetail.getTelephoneNumber(), 
					accountDetail.getEmail(), accountDetail.getNickName(), accountDetail.getPassword(), 
					accountDetail.getPrivilegeRole(), accountDetail.getRegistrationTime());
		}
		catch(Exception cex) {
			throw new CypherException("注册失败");
		}
		
	}

	
	@Override
	public void changePassword(LoginVO accountViewDetail) throws CypherException {
		// TODO Auto-generated method stub
		
	}

}
