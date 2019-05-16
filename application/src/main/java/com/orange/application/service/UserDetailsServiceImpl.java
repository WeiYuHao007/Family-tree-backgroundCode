package com.orange.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orange.application.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AllService service;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Account user = service.getUserByName(username);
		
		
		return changeToUser(user,user.getRole());
	}
	
	public UserDetails changeToUser (Account user, List<String> roleList) {
		List<GrantedAuthority> authorityList = new ArrayList<>();
		for (String role: roleList) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role);
			authorityList.add(authority);
		}
		
		UserDetails userDetails = new User(user.getName(),user.getPassword(),authorityList);
		return userDetails;
	}
}
