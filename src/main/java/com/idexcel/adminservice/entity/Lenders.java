package com.idexcel.adminservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document(collection="Lenders")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Lenders {
	
	@Id
	private String _id;
	
	private String name;

	private Address address;
	
	private String status;
	
	private String createdBy;
	
	private String createdDate;
	
	private String updatedBy;
	
	private String updatedDate;

}
