package com.orange.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//ComponentScan(lazyInit="false")默认不进行延迟初始化
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
