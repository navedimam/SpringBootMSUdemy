package com.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartmentNameAlreadyPresentException extends RuntimeException{

	public DepartmentNameAlreadyPresentException(String departmentName) {
		
		super(String.format( "Department with the name %s is already present" , departmentName));
	}
}
