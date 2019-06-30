package com.idexcel.adminservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {
	

	private String street;

	private String city;
	
	private String state;
	
	private String zipcode;
	
	private String country;

}
