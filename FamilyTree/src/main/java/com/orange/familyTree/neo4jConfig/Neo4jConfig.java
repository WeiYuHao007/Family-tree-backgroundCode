package com.orange.familyTree.neo4jConfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("neo4jConfig")
@PropertySource(value= {"classpath:neo4jconfig.properties"},
		ignoreResourceNotFound=true,encoding="UTF-8",name="neo4jconfig.properties")
//定义neo4j接口扫描包路径
@EnableNeo4jRepositories(basePackages = "com.orange.familyTree.repository")
//定义实体bean扫描包路径
@EntityScan(basePackages="com.orange.familyTree.entity")
@EnableTransactionManagement
public class Neo4jConfig {
	
}
