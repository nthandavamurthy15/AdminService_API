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
import com.idexcel.adminservice.service.ServiceInterface;

@Service
public class AdminServiceImpl implements ServiceInterface{
	
	@Autowired
	private AdminServiceDAO theAdminServiceDAO;
	
	
	EntityModelMapper theModelMapper = new EntityModelMapper();
	
	
	
	
	

	@Override
	public List<Lenders> findAll() {
		
		List<Lenders> theLenders = this.theAdminServiceDAO.findAll(Sort.by(Direction.DESC, "createdDate"));
		
		if (theLenders == null)
			return Collections.emptyList();
				
		return theLenders;
	}



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
	@Override
	public Lenders getById (String _id) {
	
		 return this.theAdminServiceDAO.findById(_id).orElse(null);
	}



	public void updateById(Lenders theLenders, String _id) {
		this.theAdminServiceDAO.save(theLenders);
	}



	public void deleteById(String _id) {
		this.theAdminServiceDAO.deleteById(_id);
		
	}
	
	public void updateStatus(LendersPatchDto lendersPatchDto, String _id) {
		
		
		Lenders theLenders = this.theAdminServiceDAO.findById(_id).orElse(null);
		
		theLenders.setStatus(lendersPatchDto.getStatus());	
		this.theAdminServiceDAO.save(theLenders);
		
		
	}
	public ResponseEntity<String> getMetaData(String _id) {
		
		if (theAdminServiceDAO.existsById(_id)) 
			return new ResponseEntity<>(HttpStatus.OK);
		else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
