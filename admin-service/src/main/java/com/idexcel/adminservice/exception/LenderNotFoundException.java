package com.idexcel.adminservice.exception;

public class LenderNotFoundException extends RuntimeException {

	public LenderNotFoundException() {
		
	}

	public LenderNotFoundException(String message) {
		super(message);
		
	}

	public LenderNotFoundException(Throwable cause) {
		super(cause);
		
	}

	public LenderNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LenderNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
