package com.idexcel.adminservice.dto;

import javax.validation.constraints.NotNull;

import com.idexcel.adminservice.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AdminServiceDTO {
	
	@NotNull
	private String name;
	
	@NotNull
	private Address address;
	

}
