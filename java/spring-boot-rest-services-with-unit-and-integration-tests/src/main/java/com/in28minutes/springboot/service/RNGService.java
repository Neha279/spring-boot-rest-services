package com.in28minutes.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public class RNGService {
    private final SecureRandom random;

    @Autowired
    public RNGService(SecureRandom random) {
        this.random = random;
    }

    public BigInteger random() {
        return new BigInteger(130, random);
    }
}
