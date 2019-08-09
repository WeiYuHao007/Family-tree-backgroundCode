package com.orange.familyTree.dao.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.neo4j.Genealogy;

@Repository
public interface GenealogyCrudRepository extends Neo4jRepository<Genealogy, Long>{
	
	//根据族谱名查询，返回族谱所有信息
	@Query("MATCH(u:User{userId:{userId}})-[:FOCUS_ON]->(g:Genealogy) \n" +
			"RETURN g.name")
	List<String> findAllGenealogy(@Param("userId") Long userId);
	
	@Query("MATCH(g:Genealogy)\r \n" + 
			"WHERE g.name = {genealogyName}\r \n" + 
			"RETURN g.followers")
	List<Long> findGenealogyFollowers(@Param("genealogyName") String genealogyName);
}
