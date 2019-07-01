package com.idexcel.adminservice.dto;

import com.idexcel.adminservice.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAllDTO {

private String _id;
	
	private String name;

	private Address address;
	
	private String status;
	
	private String createdBy;
	
	private String createdDate;
	
	private String updatedBy;

}
