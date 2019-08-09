package com.orange.familyTree.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Configuration
@PropertySource(value = {"classpath:myBatisConfig.properties"},
    ignoreResourceNotFound = false, encoding = "UTF-8", name = "myBatisConfig.properties")
@MapperScan(basePackages = "com.orange.familyTree.dao.mysql",
    annotationClass = Repository.class)
public class MyBatisConfig {
}
