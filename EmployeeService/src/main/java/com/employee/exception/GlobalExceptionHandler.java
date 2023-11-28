package com.employee.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResoureceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("USER_NOT_FOUND");
		response.setMessage(ex.getMessage());
		response.setTimeStamp(LocalDate.now());
		response.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(value = EmailAlreadyPresentException.class)
	public ResponseEntity<ErrorResponse> handleEmailAlreadyPresentException(EmailAlreadyPresentException ex, WebRequest webRequest){
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode("EMAIL_ALREADY_PRESENT");
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
