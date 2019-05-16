package com.orange.application.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import com.orange.application.entity.People;

@Component
public interface Peoplerepository extends Neo4jRepository<People, Long>  {
	
	@Query("MATCH(p:student) RETURN p")
	List<People> findAll();
	
}
