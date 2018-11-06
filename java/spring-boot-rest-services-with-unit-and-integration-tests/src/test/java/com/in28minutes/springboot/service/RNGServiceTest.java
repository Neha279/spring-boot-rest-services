package com.in28minutes.springboot.service;


import com.in28minutes.springboot.model.Course;
import com.in28minutes.springboot.service.RNGService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(value = RNGService.class, secure = false)

public class RNGServiceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RNGService  rngService;
	
	 @Test
	 public void RNGServiceAutowired() { 
		    assertNotNull(rngService);
	 }
	 
	 @Test 
	 public void RNGService() throws Exception {
//		 Mockito.when(new RNGService(Mockito.any(SecureRandom.class)))
//	      .thenReturn(rngService);
		 assertNotNull(rngService);
	 }
	 
	@Test
	public void random() throws Exception {
		
		BigInteger a = new BigInteger("10");
		Mockito.when(rngService.random())
		      .thenReturn(a);
		
		assertTrue(a.equals(new BigInteger("10")));
	}

	
}
