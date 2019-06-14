package com.idexcel.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idexcel.adminservice.dto.AdminServiceDTO;
import com.idexcel.adminservice.dto.LendersPatchDto;
import com.idexcel.adminservice.entity.Lenders;
import com.idexcel.adminservice.serviceimpl.AdminServiceImpl;

@RestController
@RequestMapping("/api")
public class AdminRestController {
	
	
	@Autowired
	private AdminServiceImpl theAdminServiceImpl;
	
	
	
	@GetMapping("/Lenders")
	public List<Lenders> getAllLenders(){
		
		return this.theAdminServiceImpl.findAll();
		
	}
	
	@PostMapping("/Lenders")
	public Lenders addLender (@RequestBody AdminServiceDTO theServiceDTO) {
		
		Lenders theLender = theAdminServiceImpl.saveLender(theServiceDTO);
		
		return theLender;
	}
	
	@GetMapping("/Lenders/{_id}")
	public Lenders getLenderById(@PathVariable String _id) {
		
		Lenders theLender = this.theAdminServiceImpl.getById(_id);
		
		return theLender;
		
	}
	
	@PutMapping("/Lenders/{_id}")
	public void updatebyId (@RequestBody Lenders theLenders, @PathVariable String _id ) {
		
		this.theAdminServiceImpl.updateById(theLenders, _id);
		
	}
	
	@DeleteMapping("/Lenders/{_id}")
	public void deleteById (@PathVariable String _id) {
		
		this.theAdminServiceImpl.deleteById(_id);
	}
	
	@PatchMapping("/Lenders/{_id}/status")
	public void updateStatus(@RequestBody LendersPatchDto patchDto, @PathVariable String _id) {
		
		this.theAdminServiceImpl.updateStatus(patchDto, _id);
	}
	
	@GetMapping("/Lenders/{_id}")
	public ResponseEntity<String> getMetadata(@PathVariable String _id) {
		
		return this.theAdminServiceImpl.getMetaData(_id);
		
	}
	
	
	

}
