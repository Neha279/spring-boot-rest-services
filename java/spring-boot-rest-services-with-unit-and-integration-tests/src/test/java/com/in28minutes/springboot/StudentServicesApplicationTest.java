package com.in28minutes.springboot;

import com.in28minutes.springboot.controller.StudentController;
import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.model.Student;
import com.in28minutes.springboot.service.RNGService;
import com.in28minutes.springboot.service.StudentService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;


@ComponentScan(basePackageClasses = {
		StudentService.class,
		RNGService.class,
		Student.class,
		Course.class
})
@ContextConfiguration(classes= {StudentService.class, RNGService.class,
		StudentController.class, Student.class, Course.class})
@SpringBootTest
public class StudentServicesApplicationTest {
	
	@Autowired
    protected WebApplicationContext webApplicationContext;
	
	@MockBean
	private StudentService  studentService;
	
	@MockBean
	private RNGService  rngService;
	

	@Test
	public void contextLoads() {
		StudentServicesApplication.main(new String[] {});
	}
	
}
