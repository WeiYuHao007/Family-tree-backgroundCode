package com.orange.familyTree.neo4jConfig;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration("neo4jConfig")
// 定义neo4j接口扫描包路径
@EnableNeo4jRepositories(basePackages = "com.orange.familyTree.repository")
// 定义实体bean扫描包路径
@EntityScan(basePackages="com.orange.familyTree.entity")
@EnableTransactionManagement
public class Neo4jConfig {
	
    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        ConfigurationSource properties = new ClasspathConfigurationSource("neo4jconfig.properties");
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
        return configuration;
    }

}
