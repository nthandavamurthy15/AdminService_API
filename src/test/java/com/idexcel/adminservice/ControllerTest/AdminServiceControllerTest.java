package com.idexcel.adminservice.ControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.idexcel.adminservice.controller.AdminRestController;
import com.idexcel.adminservice.dto.AdminServiceDTO;
import com.idexcel.adminservice.dto.GetAllDTO;
import com.idexcel.adminservice.dto.LendersPatchDto;
import com.idexcel.adminservice.entity.Address;
import com.idexcel.adminservice.entity.Lenders;
import com.idexcel.adminservice.serviceimpl.AdminServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminRestController.class)
public class AdminServiceControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private AdminServiceImpl theAdminServiceImpl;
	
	
	
	/*
	@Test
	public void testGetAllLenders() throws Exception {
		
		when(theAdminServiceImpl.findAll()).thenReturn(Arrays.asList(new GetAllDTO ("12345678", "Commercia Bank", new Address("459 Herndon Parkway", "Ashburn", "VA","20148", "USA"), "Active", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019", "Praveen K"), 
				new GetAllDTO ("65425333", "Columbia Bank", new Address("12888 Sunrise Valley Dr", "Herndon", "VA","20171", "USA"), "active", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019", "Praveen K")));
	
		RequestBuilder request = MockMvcRequestBuilders.get("/api/Lenders");
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"\"name\":\"Columbia Bank\",\"address\":{\"street\":\"12888 Sunrise Valley Dr\",\"city\":\"Herndon\",\"state\":\"VA\",\"zipcode\":\"20171\",\"country\":\"USA\"},\"status\":\"active\",\"createdBy\":\"Praveen K\",\"createdDate\":\"Sun Jun 09 03:11:17 EDT 2019\",\"updatedBy\":\"Praveen K\"},{\"_id\":\"12345678\",\"name\":\"Commercia Bank\",\"address\":{\"street\":\"459 Herndon Parkway\",\"city\":\"Ashburn\",\"state\":\"VA\",\"zipcode\":\"20148\",\"country\":\"USA\"},\"status\":\"Active\",\"createdBy\":\"Praveen K\",\"createdDate\":\"Sun Jun 09 03:11:17 EDT 2019\",\"updatedBy\":\"Praveen K\"}]"))
				.andReturn();
		
		
		
			
	
	}
	*/
	
	@Test
	public void testPostLenders() throws Exception {
		
		AdminServiceDTO adminServiceDto = new AdminServiceDTO("City Bank", new Address("12853 Elden Street", "Herndon", "VA", "20171","USA"));
		when(theAdminServiceImpl.saveLender(adminServiceDto)).thenReturn("5768542");
		
		RequestBuilder request = MockMvcRequestBuilders.post("/api/Lenders").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				//.andExpect(status().isOk())
				.andExpect(status().isBadRequest())
				.andReturn();
		
	
	}
	
	@Test
	public void testGetLenderById() throws Exception {
		
		String _id = "12345678";
		when(theAdminServiceImpl.getById(_id)).thenReturn( new Lenders ("12345678", "Commercia Bank", new Address("459 Herndon Parkway", "Ashburn", "VA","20148", "USA"), "Active", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019"));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/api/Lenders/12345678");
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"_id\":\"12345678\",\"name\":\"Commercia Bank\",\"address\":{\"street\":\"459 Herndon Parkway\",\"city\":\"Ashburn\",\"state\":\"VA\",\"zipcode\":\"20148\",\"country\":\"USA\"},\"status\":\"Active\",\"createdBy\":\"Praveen K\",\"createdDate\":\"Sun Jun 09 03:11:17 EDT 2019\",\"updatedBy\":\"Praveen K\",\"updatedDate\":\"Sun Jun 09 03:11:17 EDT 2019\"}"))
				.andReturn();
		
	}
	
	@Test
	public void testPutMapping() throws Exception {
		
		String _id = "12345678";
		Lenders theLender = new Lenders ("12345678", "Commercia Bank", new Address("459 Herndon Parkway", "Ashburn", "VA","20148", "USA"),"Not Active", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019", "Praveen K", "Sun Jun 09 03:11:17 EDT 2019");
		theAdminServiceImpl.updateById(theLender, _id);
		
		RequestBuilder request = MockMvcRequestBuilders.put("/api/Lenders/12345678").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
			   .andExpect(status().isBadRequest())
			   .andReturn();
		
	
	}
	
	@Test
	public void testDeleteById() throws Exception {
		
		String _id = "5768542";
		theAdminServiceImpl.deleteById(_id);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/Lenders/5768542");
		
		mockMvc.perform(request)
		   .andExpect(status().isOk())
		   .andReturn();

		
		
	}
	
	@Test
	public void patchById() throws Exception{
		String _id = "12345678";
		LendersPatchDto theLendersPatchDto = new LendersPatchDto ("12345678", "Not Active");
		theAdminServiceImpl.updateStatus(theLendersPatchDto, _id);
		
		
		RequestBuilder request = MockMvcRequestBuilders.patch("/api/Lenders/5768542/status");
				
		mockMvc.perform(request)
			   .andExpect(status().isBadRequest())
			   .andReturn();

		
	}
	
	

	

}
