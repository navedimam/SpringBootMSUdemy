package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyPresentException extends RuntimeException{


	public EmailAlreadyPresentException(String email) {
		
		super(String.format( "User with the email %s is already present" , email));
	}
	
	
}
