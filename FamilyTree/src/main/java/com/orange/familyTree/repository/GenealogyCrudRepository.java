package com.orange.familyTree.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.Genealogy;

@Repository
public interface GenealogyCrudRepository extends Neo4jRepository<Genealogy, Long>{
	
	//根据族谱名查询，返回族谱所有信息
	//简单尝试自带查询方法
	@Query("MATCH(a:Account{telephoneNumber:{phoneNum}})-[:FOCUS_ON]->(g:Genealogy)\n" + 
			"RETURN g.name")
	List<String> findAllGenealogy(@Param("phoneNum") Integer phoneNum);

}
