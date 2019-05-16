package com.orange.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("neo4jConfig")
@PropertySource(value= {"classpath:neo4j.properties"},
	ignoreResourceNotFound=true,encoding="UTF-8",name="neo4j.properties")
//载入属性文件
//不使用的话在注入值的时候应该采用
//@Value("${spring.data.neo4j.****}")
@ConfigurationProperties(prefix="spring.data.neo4j")
//定义Neo4j接口扫描包路径
@EnableNeo4jRepositories(basePackages="com.orange.application.dao")
//定义实体Bean扫描包路径
@EntityScan(basePackages="com.orange.application.entity")
@EnableTransactionManagement
public class Neo4jConfig {
}
