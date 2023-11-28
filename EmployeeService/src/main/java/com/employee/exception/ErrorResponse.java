package com.employee.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
	
	private LocalDate timeStamp;
	
	private String message;
	
	private String path;
	
	private String errorCode;
}
