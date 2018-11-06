package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.model.Student;
import com.in28minutes.springboot.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;
	
	Course mockCourse = new Course("Course1", "Spring", "10Steps",
			Arrays.asList("Learn Maven", "Import Project", "First Example",
					"Second Example"));

	String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	String exampleCourseListJson = "[{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}]";

	Student testStudent = new Student("testStudent1", "jyoti khanna", "Hiker, Programmer and Architect",new ArrayList<>(Arrays
			.asList(mockCourse)));

	String exampleStudentJson = "{\"id\":\"testStudent1\",\"name\":\"jyoti khanna\",\"description\":\"Hiker, Programmer and Architect\",\"courses\":[{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}]}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(
				studentService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students/Student1/courses/Course1").accept(
				MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{id:Course1,name:Spring,description:10Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void createStudentCourse() throws Exception {
		Course mockCourse = new Course("1", "Smallest Number", "1",
				Arrays.asList("1", "2", "3", "4"));

		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				studentService.addCourse(Mockito.anyString(),
						Mockito.any(Course.class))).thenReturn(mockCourse);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students/Student1/courses")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/Student1/courses/1",
				response.getHeader(HttpHeaders.LOCATION));

	}
	
	@Test
	public void registerStudent() throws Exception {
		
		Mockito.when(
			studentService.addCourse(Mockito.anyString(),
					Mockito.any(Course.class))).thenReturn(mockCourse);
	
		Mockito.when(
				studentService.addStudent(
						Mockito.any(Student.class))).thenReturn(testStudent);
		
		Mockito.when(
				studentService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students/testStudent1")
				.accept(MediaType.APPLICATION_JSON).content(exampleStudentJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getStatus());
		
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

	}
	
	@Test
	public void retriveStudent() throws Exception {
		
			Mockito.when(
			studentService.addCourse(Mockito.anyString(),
					Mockito.any(Course.class))).thenReturn(mockCourse);
	
			Mockito.when(
					studentService.addStudent(
							Mockito.any(Student.class))).thenReturn(testStudent);
			  
			Mockito.when(
					studentService.retrieveStudent(Mockito.anyString()))
					.thenReturn(testStudent);
			
			RequestBuilder  requestBuilder = MockMvcRequestBuilders
				.get("/students/testStudent1")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON);
			
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			JSONAssert.assertEquals(exampleStudentJson, result.getResponse().getContentAsString(), false);

	

	}
	
	@Test 
	public void retriveCoursesForStudent() throws Exception{
		
		List<Course> courseList = new ArrayList<>(Arrays.asList(mockCourse));
		Mockito.when(
					studentService.retrieveCourses(Mockito.anyString()))
			        .thenReturn(courseList);
			
		RequestBuilder  requestBuilder = MockMvcRequestBuilders
			.get("/students/Student1/courses")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	
		System.out.println("result.getResponse()=" + result.getResponse().getContentAsString() );
	
		JSONAssert.assertEquals(exampleCourseListJson, result.getResponse()
					.getContentAsString(), false);

	}

}
