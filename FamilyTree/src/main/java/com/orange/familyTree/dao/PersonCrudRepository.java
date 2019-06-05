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
	
	//判断是否节点存在
	
	//查询两个节点间的最短路中节点的总数
	@Query("MATCH(p1:Person{name:{startPerson}}),(p2:Person{name:{endPerson}}),"
			+ "p=shortestpath((p1)-[:IS_SON|:IS_BROTHER|:IS_FARTHER*]-(p2))"
			+ "RETURN length(p)")
	int getShortPathLength(@Param("startPerson") String startPerson, 
			@Param("endPerson") String endPerson);
	
	//查询两节点间的最短路径中指定位置的人
	@Query("MATCH(p1:Person{name:{startPerson}}),(p2:Person{name:{endPerson}}),"
			+ "p=shortestpath((p1)-[:IS_SON|:IS_BROTHER|:IS_FARTHER*]-(p2)) "
			+ "RETURN nodes(p)[{index}]")
	Person findShortPath(@Param("startPerson") String startPerson,
			@Param("endPerson") String endPerson, @Param("index") int index);
	
	
	//Delete
	//根据节点名删除节点
	@Query("MATCH(p1:Person{name:{name}})-[r]-(s)"
			+ "DELETE p1,r")
	void deleteByName(String name);
	
	
	//Post
	@Query("MATCH(p1:Person{name:{startName}}),(p2:Person{name:{endName}})"
			+ "CREATE (p1)-[:{relationshipName}]->(p2)")
	void createRelationship(@Param("startName") String startName, 
			@Param("relationshipName") String relationshipName,
			@Param("endName") String endName);
	
	
	//Put
	

}
