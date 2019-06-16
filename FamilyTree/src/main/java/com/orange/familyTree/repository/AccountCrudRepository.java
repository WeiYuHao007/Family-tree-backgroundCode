package com.orange.familyTree.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.orange.familyTree.entity.Account;

public interface AccountCrudRepository extends Neo4jRepository<Account, Long>{
	
	//通过电话号码和密码查找账户对象
	@Query("MATCH(a:Account) \n" + 
			"WHERE a.telephoneNumber={telephoneNumber} AND a.password={password} \n" + 
			"RETURN a")
	Account findByTelephoneNumberAndPassword(@Param("telephoneNumber") String telephoneNumber, 
			@Param("password") String password);
	

}
