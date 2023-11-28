package com.department.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = DepartmentNameAlreadyPresentException.class)
	public ResponseEntity<ErrorResponse> handleDepartmentNameAlreadyPresentException(
			DepartmentNameAlreadyPresentException ex, WebRequest webRequest){
		
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("DEPARTMENT_NAME_ALREADY_PRESENT");
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDate.now());
		response.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(value = DepartmentCodeAlreadyPresentException.class)
	public ResponseEntity<ErrorResponse> handleDepartmentCodeAlreadyPresentException(
			DepartmentCodeAlreadyPresentException ex, WebRequest webRequest){
		
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("DEPARTMENT_CODE_ALREADY_PRESENT");
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDate.now());
		response.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest webRequest){
		ErrorResponse response = new ErrorResponse();
		
		response.setErrorCode("INTERNAL_SERVER_ERROR");
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDate.now());
		response.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
	
}
