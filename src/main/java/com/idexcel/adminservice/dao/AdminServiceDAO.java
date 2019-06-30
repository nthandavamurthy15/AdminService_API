package com.idexcel.adminservice.dao;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.idexcel.adminservice.entity.Lenders;


public interface AdminServiceDAO extends MongoRepository<Lenders, String> {
	
	public Lenders findByName (String name);
 
	
	

}


