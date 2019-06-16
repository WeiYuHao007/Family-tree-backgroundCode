package com.orange.familyTree.repository;

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
	@Query("MATCH(a1:Account)-[:FOCUS_ON]->(g:Genealogy)-[:OWNS]->(p:Person{name:{personName}})\n" + 
			"WHERE a1.nickName={nickName} AND g.name={genealogyName}\n" + 
			"RETURN p")
	Person findByName(@Param("nickName") String nickName, @Param("genealogyName") String genealogyName,
			@Param("personName") String personName);
	
	//判断是否节点存在
	
	//查询两节点之间的最短路径
	//性能有待后续优化
	@Query("MATCH(a:Account)-[:FOCUS_ON]->(g:Genealogy)-[:OWNS]->(p1:Person{name:{startPerson}}), " + 
			"(g)-[:OWNS]->(p2:Person{name:{endPerson}}), " + 
			"p = shortestpath((p1)-[:IS_SON|:IS_BROTHER|:IS_FARTHER*]-(p2)) " + 
			"WHERE a.nickName={nickName} AND g.name={genealogyName} " + 
			"UNWIND(nodes(p)) AS persons " + 
			"RETURN persons")
	List<Person> findShortPath(@Param("nickName") String nickName, @Param("genealogyName") String genealogyName,
			@Param("startPerson") String startPerson, @Param("endPerson") String endPerson);
	
	
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
