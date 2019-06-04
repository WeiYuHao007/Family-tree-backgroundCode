package com.orange.familyTree.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.Person;

@Repository
public interface PersonCrudRepository extends Neo4jRepository<Person, Long>{
	
	//Get
	//根据节点名查询，返回被查询人的所有信息
	Person findByName(String name);
	
	//获得长辈（男）和其直系晚辈（男）之间血脉的路径
	@Query("MATCH p = (:Person{name:{p1}})-[:IS_SON*]->(:Person{name:{p2}})"
			+ "RETURN p")
	List<Person> findPersonRelationshipPath(@Param("p1") String startPersonName,
			@Param("p2") String endPersonName);
	
	
	//Delete
	//根据节点名删除节点
	@Query("MATCH(p1:Person{name:{name}}),(p1)-[r]-(s)"
			+ "DELETE p1,r")
	void deleteByName(String name);
	
	
	//Post
	@Query("MATCH(p1:Person{name:{startName}}),(p2:Person{name:{endName}})"
			+ "CREATE (p1)-[:{relationshipName}]->(p2)")
	void createRelationship(@Param("startName") String startName, 
			@Param("endName") String endName,
			@Param("relationshipName") String relationshipName);
	
	
	//Put
	

}
