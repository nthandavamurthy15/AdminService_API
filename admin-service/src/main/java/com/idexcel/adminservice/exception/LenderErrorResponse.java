package com.idexcel.adminservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LenderErrorResponse {
	
	private int status;
	private String message;
	private long timeStamp;

}
