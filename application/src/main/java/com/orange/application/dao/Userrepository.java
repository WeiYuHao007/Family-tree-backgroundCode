package com.orange.application.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.orange.application.entity.Account;

@Component
public interface Userrepository extends Neo4jRepository<Account, Long> {
	
	@Query("MATCH(u:Account) WHERE u.name = {name} RETURN u")
	Account getUserByName(@Param("name") String username);
	@Query("MATCH(u:Account) WHERE u.name ={name} RETURN u.role")
	List<String> findRolesByUserName(@Param("name") String name);

}
