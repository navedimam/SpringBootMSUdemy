package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private String resourceName;
	
	private String feildName;
	
	private Long feildValue;

	public ResourceNotFoundException(String resourceName, String feildName, Long feildValue) {
		super(String.format("%s not found with %s : %s", resourceName,feildName,feildValue));
		this.resourceName = resourceName;
		this.feildName = feildName;
		this.feildValue = feildValue;
	}
	
	
}
