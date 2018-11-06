package com.in28minutes.springboot.service;

import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.model.Student;
import com.in28minutes.springboot.service.StudentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfiguration.class) 
public class StudentServiceTest {
		
	 @Autowired
	 private StudentService studentService;
	
	 @Test
	 public void StudentServiceAutowired() { 
		    assertNotNull(studentService);
	 }
	 
	Course mockcourse = new Course("Course1", "Spring", "10Steps",
			Arrays.asList("Learn Maven", "Import Project", "First Example",
					"Second Example"));
	
	Student testStudent = new Student("testStudent1", "jyoti khanna", "Hiker, Programmer and Architect",new ArrayList<>(Arrays
			.asList(mockcourse)));
	
	
	@Test
	public void retrieveStudent() throws Exception{
		
		Student addStudent = studentService.addStudent(testStudent);
	    Student found = studentService.retrieveStudent(addStudent.getId());
	    assertEquals(found, addStudent);
	    assertTrue(found.equals(addStudent));
}
	
	@Test
	public void reteriveCourse() throws Exception{
		
		Student addStudent = studentService.addStudent(testStudent);
	    Course addCourse = studentService.addCourse(addStudent.getId(), mockcourse);
	    Course found = studentService.retrieveCourse(addStudent.getId(), mockcourse.getId());
	    assertTrue(found.equals(addCourse));
	    
	    /*not valid student fails*/
	    Course notFound = studentService.retrieveCourse("dummy", mockcourse.getId());
	    assertNull(notFound);
	    
	    /*not valid course fails*/
	    notFound = studentService.retrieveCourse(addStudent.getId(), "dummyCourse");
	    assertNull(notFound);
	    
	}
	
	
	@Test
	public void addStudent() throws Exception {
		
		Student addStudent = studentService.addStudent(testStudent);
		Student found = studentService.retrieveStudent(addStudent.getId());
	    System.out.println("found " + found.getId());
	    assertTrue(found.equals(addStudent));
		
	}

	@Test
	public void addCourse() throws Exception{
		
		Student addStudent = studentService.addStudent(testStudent);
		Course addCourse = studentService.addCourse(addStudent.getId(), mockcourse);
	    Course found = studentService.retrieveCourse(addStudent.getId(), mockcourse.getId());
	    assertTrue(found.equals(addCourse));
	    
	    /*not valid student fails*/
	    Course notFound = studentService.addCourse("dummy", mockcourse);
		assertNull(notFound);
			
	}
	
	@Test
	public void retriveAllStudents() throws Exception{

		List<Student> testStudentList = studentService.retrieveAllStudents();
		 assertNotNull(testStudentList);
		
	}
    
	@Test
	public void retrieveCourses() throws Exception{
	    
		Student addStudent = studentService.addStudent(testStudent);
		Course addCourse = studentService.addCourse(addStudent.getId(), mockcourse);
		Student found = studentService.retrieveStudent(addStudent.getId());
		
		List<Course> courses = studentService.retrieveCourses(found.getId());
		assertTrue(courses.contains(addCourse));
		
		courses = studentService.retrieveCourses("dummy");
		assertNull(courses);
		   
	}
}
