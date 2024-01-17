package com.school.sba.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.school.sba.exception.SchoolNotFoundException;

@RestControllerAdvice
public class SchoolExcepitinHandler {

	private ExceptionResponce<String> structure;
	
	@ExceptionHandler(SchoolNotFoundException.class)
	public ResponseEntity<ExceptionResponce<String>> invalidFormat(SchoolNotFoundException schoolNotFoundException)
	
	{
		
		structure.setMessage(schoolNotFoundException.getMessage());
		structure.setRootCause("PLEASE ENTER PASSWORD IN A VALID SCHOOL");
		structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<ExceptionResponce<String>>(structure,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
}
