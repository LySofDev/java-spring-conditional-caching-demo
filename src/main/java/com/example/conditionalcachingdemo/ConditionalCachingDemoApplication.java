package com.example.conditionalcachingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConditionalCachingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConditionalCachingDemoApplication.class, args);
	}

}
