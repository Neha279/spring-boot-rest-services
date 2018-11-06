package com.in28minutes.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.in28minutes.springboot.model", 
		"com.in28minutes.springboot.controller", "com.in28minutes.springboot.service"})
@ComponentScan(basePackages = "com.in28minutes.springboot")
public class StudentServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServicesApplication.class, args);
	}
}
