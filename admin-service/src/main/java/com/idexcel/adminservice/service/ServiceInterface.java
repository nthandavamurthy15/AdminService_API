package com.idexcel.adminservice.service;

import java.util.List;


import com.idexcel.adminservice.dto.AdminServiceDTO;
import com.idexcel.adminservice.dto.LendersPatchDto;
import com.idexcel.adminservice.entity.Lenders;

public interface ServiceInterface {
	
	List<Lenders> findAll();
	
	String saveLender(AdminServiceDTO theAdminDto);
	
	Lenders getById(String _id);
	
	void updateById(Lenders theLenders, String _id);
	
	void deleteById(String _id);
	
	void updateStatus(LendersPatchDto theLendersPatchDto, String _id);
	
}
