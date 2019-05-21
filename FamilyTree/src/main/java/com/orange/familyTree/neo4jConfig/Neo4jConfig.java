package com.orange.familyTree.neo4jConfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration("neo4jconfig")
@PropertySource(value = { "classpath:neo4jconfig" }, 
		ignoreResourceNotFound=true, encoding = "UTF-8", name = "neo4jconfig")
@EnableNeo4jRepositories(basePackages = "com.orange.familyTree.dao")
@EntityScan(basePackages = "com.orange.familyTree.entity")
public class Neo4jConfig {

}
