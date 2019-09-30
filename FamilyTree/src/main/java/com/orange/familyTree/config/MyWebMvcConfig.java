package com.orange.familyTree.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Value("${file.avatar.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.avatar.uploadFolder}")
    private String uploadFolder;

    @Value("${file.avatar.loadFolder}")
    private String loadFolder;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8080")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations(loadFolder);
    }

    public String getUploadFolder() {
        return uploadFolder;
    }

    public void setUploadFolder(String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public String getStaticAccessPath() {
        return staticAccessPath;
    }

    public void setStaticAccessPath(String staticAccessPath) {
        this.staticAccessPath = staticAccessPath;
    }

    public String getLoadFolder() {
        return loadFolder;
    }

    public void setLoadFolder(String loadFolder) {
        this.loadFolder = loadFolder;
    }
}
