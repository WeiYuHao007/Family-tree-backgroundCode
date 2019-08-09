package com.orange.familyTree.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = {"classpath:neo4jConfig.properties"},
ignoreResourceNotFound = false,encoding = "UTF-8",name = "neo4j.properties")
//定义neo4j接口扫描包路径
@EnableNeo4jRepositories(basePackages = "com.orange.familyTree.dao.neo4j")
@EntityScan(basePackages="com.orange.familyTree.entity.neo4j")
@EnableTransactionManagement
public class Neo4jConfig {

}
