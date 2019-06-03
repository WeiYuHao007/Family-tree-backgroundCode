package com.orange.familyTree.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.orange.familyTree.entity.Account;

public interface AccountCrudRepository extends Neo4jRepository<Account, Long>{

}
