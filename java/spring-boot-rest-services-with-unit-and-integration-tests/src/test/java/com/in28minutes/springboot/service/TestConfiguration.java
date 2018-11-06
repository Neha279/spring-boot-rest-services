package com.in28minutes.springboot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.model.Student;

@ComponentScan(basePackageClasses = {
		StudentService.class,
		RNGService.class,
		Student.class,
		Course.class
})
@SpringBootApplication
public class TestConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(TestConfiguration.class, args);
    }
}