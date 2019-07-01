package com.idexcel.adminservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idexcel.adminservice.dto.AdminServiceDTO;
import com.idexcel.adminservice.dto.LendersPatchDto;
import com.idexcel.adminservice.entity.Lenders;
import com.idexcel.adminservice.serviceimpl.AdminServiceImpl;

@RestController
@RequestMapping("/navaneeth")
public class AdminRestController {
	
	
	@Autowired
	private AdminServiceImpl theAdminServiceImpl;
	
	
	
	@GetMapping("/Lenders")
	public List<Lenders> getAllLenders(){
		
		return this.theAdminServiceImpl.findAll();
		
	}
	
	@PostMapping("/Lenders")
	public ResponseEntity<Object> addLender (@RequestBody AdminServiceDTO theServiceDTO) {
		
		String id = theAdminServiceImpl.saveLender(theServiceDTO);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{_id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(location).build();
		
		
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
	
	
	@RequestMapping(value= "/Lenders/{LenderId}", method = RequestMethod.HEAD)
	
	public ResponseEntity <String> returnHeader(@PathVariable String LenderId) {
		
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.set("Admin-Service-Header", "Contains the Lender Information");
		ResponseEntity <String> theResponseEntity = new ResponseEntity<String>("Header Information of the Admin Servie", responseHeader, HttpStatus.OK);
		
		if (theAdminServiceImpl.checkLender(LenderId))	{		
			
			return theResponseEntity;
		}
		return theResponseEntity;	
		
		 
	}
	
	/*
	 * Communicating with the Provided Rest API and the end point
 	 * displays the JSON data.d
	 */
	
	@GetMapping("/infofromtodo")
	public ResponseEntity <String> getnfoFormRest(){
		
		RestTemplate restTemplate = new RestTemplate ();
		String todosURL = "https://jsonplaceholder.typicode.com/todos";
		
		ResponseEntity<String> response = restTemplate.getForEntity(todosURL + "/1", String.class);
		
		return response;
		
		
	}

}
