package com.in28minutes.springboot.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;


@Component
@Service
public class RNGService {
   
	private SecureRandom random = new SecureRandom();
	
    public RNGService() {}
    
    public RNGService(SecureRandom random) {
        this.random = random;
    }

    public BigInteger random() {
        return new BigInteger(130, random);
    }
}
