package com.orange.familyTree.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.Person;

@Repository
public interface RetrieveRepository extends Neo4jRepository<Person, Long>{
	
	//根据人的名字查询
	@Query("MATCH(p:Person) WHERE p.name={name} RETURN p")
	Person findByName(@Param("name") String name);
	//查询近亲
	@Query("MATCH(p1:Person{name:{name}})-[r]->(p2:Person) Return p2")
	List<Person> findCloseRelativeByName(@Param("name") String name);

}
