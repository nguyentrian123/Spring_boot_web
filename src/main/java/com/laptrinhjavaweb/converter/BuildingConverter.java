package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEdit;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RentAreaConverter rentAreaConverter;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	// convert cho tìm kiếm tòa nhà 
	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
		dto.setAddress(dto.getStreet() +", "+ dto.getWard() +", "+dto.getDistrict());
		return dto;
	}
	
	// covert cho chi tiết tòa nhà
	public BuildingDTO convertToResponse(BuildingEntity item) {
		BuildingDTO dto = modelMapper.map(item, BuildingDTO.class);
		return dto;
	}
	

	public BuildingEdit convertToEdit(BuildingEntity item) {
		BuildingEdit dto = modelMapper.map(item, BuildingEdit.class);
		List<RentAreaEntity> rentAreaEntity = rentAreaRepository.findByBuildingId(item.getId());
		List<String> aList = new ArrayList<>();	
		
		// chuyeern  1 cái mảng thành 1 arraylist
		if(item.getType() != null )
		{
			dto.setType(Arrays.asList(item.getType().split(","))); // đưa xuống thì ta cần 1 cái chuỗi, nhưng lấy lên thì cần tách ra 1 cái list
		}
			
		for(RentAreaEntity r : rentAreaEntity)
		{
			String s = String.valueOf(r.getValue());
			aList.add(s);
		}
		String emptyArea  = String.join(",", aList);
		dto.setRentArea(emptyArea); // set hiển thị rentare theo định dạng value,value,
		
		return dto;
	}
	
	// toEntityWithDTO
	public BuildingEntity convertToEntity(BuildingDTO newBuilding) {
		BuildingEntity entity = modelMapper.map(newBuilding, BuildingEntity.class);
		return entity;
	}
	
	// toEntityWithEditWhenSave
	public BuildingEntity convertToEntity(BuildingEdit newBuilding) {
	
		
		BuildingEntity entity = modelMapper.map(newBuilding, BuildingEntity.class);
		if(newBuilding.getType() != null) {
			String  type  = String.join(",", newBuilding.getType());
			entity.setType(type);
		}
		
		
		return entity;
	}
	
	// toEntityWithEditWhenUpdate
	public BuildingEntity convertToEntity(BuildingEntity result, BuildingEdit edit)
	{
		
		
		result = modelMapper.map(edit, BuildingEntity.class);
		if(edit.getType() != null)
		{
			String  type  = String.join(",", edit.getType());
			result.setType(type);
		}
		
	
		
		return result;
	}
	


}
