package com.Ibase.config;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class IbaseBasicAuthUtils {
	
	public String generateBasicToken(String userName, String password) {
		String input = userName + ":" + password;
	    return Base64.getEncoder().encodeToString(input.getBytes());
	}

}
