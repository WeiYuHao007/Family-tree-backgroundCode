package com.orange.application.service;

import java.util.List;

import com.orange.application.entity.People;
import com.orange.application.entity.Account;

public interface AllService {
	List<People> findAll();
	Account getUserByName(String username);
	List<String> findRolesByUserName(String username);
}
