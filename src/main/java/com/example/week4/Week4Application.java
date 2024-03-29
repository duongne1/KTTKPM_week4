package com.example.week4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
public class Week4Application {

	public static void main(String[] args) {
		SpringApplication.run(Week4Application.class, args);
	}

}
