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
	// 查询指定节点的儿子
	@Query("MATCH (g)-[:OWNS]->(p1:Person)<-[r]-(p2:Person)\r\n" + 
			"WHERE g.name = {genealoguName} AND p1.name = {sourcePersonName} AND type(r) = 'IS_SON'\r\n" + 
			"RETURN p2.name")
	List<String> findSons(@Param("genealoguName") String genealogyName, @Param("sourcePersonName") String sourcePersonName);
	
	// 查询指定节点的妻子与女儿
	@Query("MATCH (g)-[:OWNS]->(p1:Person)\r \n" + 
			"WHERE g.name = {genealogyName} AND p1.name = {sourcePersonName} \r \n" + 
			"MATCH (p1)<-[r]-(p2:Person)\r \n" + 
			"WHERE type(r) = 'IS_WIFE' OR type(r) = 'IS_DAUGHTER'\r \n" + 
			"RETURN p2.name")
	List<String> findWifeAndDaughter(@Param("genealogyName") String genealogyName,
			@Param("sourcePersonName") String sourcePersonName);
	
	// 查询两个相邻节点间的关系
	@Query("MATCH (p1:Person)<-[:OWNS]-(g)-[:OWNS]->(p2:Person), (p1)-[r]->(p2)" + 
			"WHERE p1.name = {startPerson} AND p2.name = {endPerson} AND g.name = {genealogyName}\r\n" + 
			"RETURN r.relationshipName")
	String findRelationship(@Param("genealogyName") String genealogyName, 
			@Param("startPerson") String startPersonName, @Param("endPerson") String endPersonName);
	
	// 查询两节点之间的最短路径（路径最长为九）
	@Query("MATCH(p2)<-[:OWNS]-(g)-[:OWNS]->(p1)\r \n" + 
			"WHERE g.name = {genealogyName} AND p1.name = {startPerson} AND p2.name = {endPerson}\r \n" + 
			"WITH p1,p2 MATCH p = shortestpath((p1)-[*..9]-(p2))\r \n" + 
			"WHERE none(r in relationships(p) WHERE type(r) = 'OWNS')\r \n" + 
			"UNWIND [p in nodes(p) | p.name] AS names\r \n" + 
			"RETURN names")
	List<String> findShortPathNodes(@Param("genealogyName") String genealogyName, 
			@Param("startPerson") String startPersonName, @Param("endPerson") String endPersonName);
	
	//Delete
	//根据节点名删除节点
	
	
	//Post

	
	//修改节点名
	
	//修改节点出生日期
	
	//修改节点死亡日期
	
	//修改节点主要成就
	
	//修改人物间的关系
	
	
	
	//Put
	

}
