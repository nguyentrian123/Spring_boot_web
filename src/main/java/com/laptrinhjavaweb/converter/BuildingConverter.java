package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
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
	
	
	
	// covert cho chi tiết tòa nhà
	public BuildingDTO convertToResponse(BuildingEntity item) {
		BuildingDTO dto = modelMapper.map(item, BuildingDTO.class);
		return dto;
	}
	

	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
		List<RentAreaEntity> rentAreaEntitys = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		
		if(buildingEntity.getType() != null )
		{
			dto.setType(Arrays.asList(buildingEntity.getType().split(","))); // đưa xuống thì ta cần 1 cái chuỗi, nhưng lấy lên thì cần tách ra 1 cái list
		}	
		String emptyArea =  rentAreaEntitys.stream().map(entity -> String.valueOf(entity.getValue())).collect(Collectors.joining(","));
		dto.setRentArea(emptyArea); //  hiển thị rentare theo định dạng value,value,
		
		return dto;
	}

	
	// toEntityWithEditWhenSave
	public BuildingEntity convertToEntity(BuildingDTO newBuilding) {
	
		
		BuildingEntity buildingEntity = modelMapper.map(newBuilding, BuildingEntity.class);
		List<String> values = Arrays.asList(newBuilding.getRentArea().split(","));
		List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
		
		for(String item : values){
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setBuilding(buildingEntity);
			rentAreaEntity.setValue(Integer.parseInt(item));
			rentAreaEntities.add(rentAreaEntity);
		}
		
		buildingEntity.setRentAreas(rentAreaEntities);
		
		if(newBuilding.getType() != null) {
			String  type  = String.join(",", newBuilding.getType());
			buildingEntity.setType(type);
		}
		
		return buildingEntity;
	}
	
	
	// toEntityWithEditWhenUpdate
	public BuildingEntity convertToEntity(BuildingEntity result, BuildingDTO buildingDTO)
	{
		result = modelMapper.map(buildingDTO, BuildingEntity.class);	
		rentAreaRepository.deleteAll(rentAreaConverter.convertToEntityDelete( result , buildingDTO));
		List<RentAreaEntity> rentAreaEntities = rentAreaConverter.convertToEntityUpdate( result , buildingDTO); 
		result.setRentAreas(rentAreaEntities); // không có cascade = CascadeType.ALL thì k save được 
		 
		if(buildingDTO.getType() != null)
		{
			String  type  = String.join(",", buildingDTO.getType());
			result.setType(type);
		}
		
		return result;
	}
	


}
