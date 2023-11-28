package com.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentCodeAlreadyPresentException extends RuntimeException{


	public DepartmentCodeAlreadyPresentException(String code) {
		
		super(String.format( "User with the email %s is already present" , code));
	}
	
	
}
