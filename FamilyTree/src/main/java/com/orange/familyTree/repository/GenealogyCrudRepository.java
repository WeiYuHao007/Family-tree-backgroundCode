package com.orange.familyTree.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.orange.familyTree.entity.Genealogy;

@Repository
public interface GenealogyCrudRepository extends Neo4jRepository<Genealogy, Long>{
	
	//根据族谱名查询，返回族谱所有信息
	Genealogy findByName(String name);

}
