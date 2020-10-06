package com.simulation.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(AlreadyExistException e1)
	{
		ErrorResponse error=new ErrorResponse();
		error.setStatus(HttpStatus.CONFLICT);
		error.setMessage(e1.getMessage());
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NullPointerException e2)
	{
		ErrorResponse error=new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(e2.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

}
