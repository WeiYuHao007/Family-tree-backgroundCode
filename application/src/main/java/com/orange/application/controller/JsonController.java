package com.orange.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orange.application.entity.People;
import com.orange.application.service.AllService;
import com.orange.application.entity.Account;

@Controller
public class JsonController{
	
	@Autowired
	private AllService Service;

	@RequestMapping("/findAll")
    @ResponseBody
	public List<People> findAll(){
    	return Service.findAll();
	}

	@RequestMapping(value="/user/{username}")
    @ResponseBody
	public Account getUserByName(@PathVariable("username") String username){
    	return Service.getUserByName(username);
	}
    
	@RequestMapping("/roles/{username}")
    @ResponseBody
	public List<String> findRolesByName(@PathVariable String username){
    	return Service.findRolesByUserName(username);
	}
}
