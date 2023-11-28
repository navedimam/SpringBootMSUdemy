package com.net.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
		 ResourceNotFoundException exception , WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDate.now());
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorCode("USER_NOT_FOUND");
		errorDetails.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_EXTENDED);
		
	}
	
	@ExceptionHandler(value = EmailNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleEmailNotFoundException(
		 EmailNotFoundException exception , WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDate.now());
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorCode("USER_WITH_THE_EMAIL_ALREADY_PRESENT");
		errorDetails.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(
		 Exception exception , WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDate.now());
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setErrorCode("INTERNAL_SERVER_ERROR");
		errorDetails.setPath(webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,String> map = new HashMap<>();
		 	
		ex.getAllErrors().forEach((error)->{
			String feildName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(feildName,message);
		});
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		
	}
}
