package com.idexcel.adminservice.dto;

import org.modelmapper.ModelMapper;


import com.idexcel.adminservice.entity.Lenders;

public class EntityModelMapper {
		
	
	
		ModelMapper modelMapper = new ModelMapper();
		
		public AdminServiceDTO convertToDTO (Lenders lenders) {
			
			AdminServiceDTO adminServiceDTO = modelMapper.map(lenders, AdminServiceDTO.class);
			
			return adminServiceDTO;
			
		}
		
		public Lenders converttoEntity (AdminServiceDTO theAdminDTO) {
			
			Lenders theLenders = modelMapper.map(theAdminDTO, Lenders.class);
			
			return theLenders;
		}
		
		public Lenders convertPatchToEntity (LendersPatchDto thePatchDto) {
					
			Lenders theLenders = modelMapper.map(thePatchDto, Lenders.class);
					
			return theLenders;
		}

		
}
