package com.idexcel.adminservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdminServiceExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity <LenderErrorResponse> handleException(LenderNotFoundException e){
		
		LenderErrorResponse errorResponse = new LenderErrorResponse( HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity <LenderErrorResponse> handleException(LenderAlreadyExistException e){
		
		LenderErrorResponse errorResponse = new LenderErrorResponse( HttpStatus.CONFLICT.value(), e.getMessage(), System.currentTimeMillis());
		
		
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity <LenderErrorResponse> handleException(Exception e){
		
		LenderErrorResponse errorResponse = new LenderErrorResponse( HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	

}
