package com.idexcel.adminservice.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idexcel.adminservice.dao.AdminServiceDAO;
import com.idexcel.adminservice.dto.AdminServiceDTO;
import com.idexcel.adminservice.dto.EntityModelMapper;
import com.idexcel.adminservice.dto.LendersPatchDto;
import com.idexcel.adminservice.entity.Lenders;
import com.idexcel.adminservice.exception.LenderAlreadyExistException;
import com.idexcel.adminservice.exception.LenderNotFoundException;
import com.idexcel.adminservice.service.ServiceInterface;


@Service
public class AdminServiceImpl implements ServiceInterface{
	
	@Autowired
	private AdminServiceDAO theAdminServiceDAO;
	
	
	EntityModelMapper theModelMapper = new EntityModelMapper();
	
	/* 
	 * The following implementations is for the implementation of GET functionality
	 * Retrieves all the data from the database and returns List to controller layer
	 */
	@Override
	public List<Lenders> findAll() {
		
		List<Lenders> theLenders = this.theAdminServiceDAO.findAll(Sort.by(Direction.DESC, "createdDate"));
		
		if (theLenders == null)
			return Collections.emptyList();
				
		return theLenders;
	}
	
	/* 
	 * The following implementations is for the implementation of POST functionality
	 * Accepts a DTO with the exposed information and maps with the Entity
	 * if the specified Lender is present, exception is thrown
	 * Added the other administrative details to push to the database 
	 */
	@Override
	public String saveLender(AdminServiceDTO theAdminDto) {
		
		String name = theAdminDto.getName();
		
		System.out.println(name);
		
		//if the lender with the name does not exist add user or return exception
		if (theAdminServiceDAO.findByName(name)==null){				
		Lenders theLenders = theModelMapper.converttoEntity(theAdminDto);
		theLenders.set_id("5768542");
		theLenders.setStatus("Active");
		theLenders.setCreatedBy("Praveen K");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); 
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		theLenders.setCreatedDate(nowAsISO);
		theLenders.setUpdatedBy("Praveen K");
		theLenders.setUpdatedDate(nowAsISO);
		
		theAdminServiceDAO.insert(theLenders);
		
		return theLenders.get_id();
		
		} else {
			throw new LenderAlreadyExistException ("the lender with the name specified already Exist" + name);
		}

		
	}
	
	
	// returns the object of specified ID
	@Override
	public Lenders getById (String _id) {
	
		 return this.theAdminServiceDAO.findById(_id).orElse(null);
	}


	//updates the data specified with the ID (PUT)
	public void updateById(Lenders theLenders, String _id) {
		this.theAdminServiceDAO.save(theLenders);
	}


	//Deletes the contents of specified ID
	public void deleteById(String _id) {
		this.theAdminServiceDAO.deleteById(_id);
		
	}
	
	/*
	 * Finds the object by ID, if exists update data, else 
	 * throw bad request exception
	 */
	public void updateStatus(LendersPatchDto lendersPatchDto, String _id) {
		
		if (theAdminServiceDAO.existsById(_id)) {
		Lenders theLenders = this.theAdminServiceDAO.findById(_id).orElse(null);
		
		theLenders.setStatus(lendersPatchDto.getStatus());	
		this.theAdminServiceDAO.save(theLenders);
		}
		
		else
			throw new LenderNotFoundException ("The Lender with the Id:"+ _id +"does not exist");
		
		
	}
	
	
	public ResponseEntity<String> getMetaData(String _id) {
		
		if (theAdminServiceDAO.existsById(_id)) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public boolean checkLender(String _id) {
		if (theAdminServiceDAO.existsById(_id))
			return true;
		
		else
			throw new LenderNotFoundException ("The Lender with the Id:"+ _id +"dows not exist" );
		
		
		
	}
	
	
	
	
}
