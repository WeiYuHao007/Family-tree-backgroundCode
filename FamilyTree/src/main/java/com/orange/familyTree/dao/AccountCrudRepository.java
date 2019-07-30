package com.orange.familyTree.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.orange.familyTree.entity.Account;

public interface AccountCrudRepository extends Neo4jRepository<Account, Long>{
	
	//通过电话号码和密码查找账户对象
	@Query("MATCH(a:Account) \n" + 
			"WHERE a.telephoneNumber={phoneNum} AND a.password={password} \n" + 
			"RETURN a")
	Account findByTelephoneNumberAndPassword(@Param("phoneNum") Integer telephoneNumber, 
			@Param("password") String password);
	
	//通过邮箱和密码查找账户对象
	@Query("MATCH(a:Account)\n" + 
			"WHERE a.email={email} AND a.password={password}\n" + 
			"return a")
	Account findByEmailAndPassword(@Param("email") String email, 
			@Param("password") String password);
	
	//注册账号
	@Query("CREATE(a:Account)\n" + 
			"SET a.telephoneNumber={phoneNum},\n" + 
			"a.email={email},\n" + 
			"a.nickName={nickName},\n" + 
			"a.password={password},\n" + 
			"a.privilegeRole={role},\n" + 
			"a.registrationTime={time}")
	void registerAccount(@Param("phoneNum") Integer telephoneNumber, @Param("email") String email,
			@Param("nickName") String nickName, @Param("password") String password, 
			@Param("privilegeRole") List<String> privilegeRole, @Param("registrationTime") String registrationTime);

}
