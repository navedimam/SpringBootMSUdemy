package com.net.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private LocalDate timeStamp;
	
	private String message;
	
	private String path;
	
	private String errorCode;
}
