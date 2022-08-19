package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.exception.FieldNotFoundException;
import com.laptrinhjavaweb.repository.RentAreaRepository;

@Component
public class RentAreaConverter {

	@Autowired
	private RentAreaRepository rentAreaRepository;
	

	public List<RentAreaEntity> convertToEntityDelete( BuildingEntity entity , BuildingDTO buildingDTO )
	{
		List<String> values = Arrays.asList(buildingDTO.getRentArea().split(",")); // đưa chuỗi về list
		List<RentAreaEntity> results = new ArrayList<>();
		List<RentAreaEntity> rentAreaEntitys = rentAreaRepository.findByBuildingId(entity.getId());
		
		for(RentAreaEntity rentAreaEntity : rentAreaEntitys)
		{
			int countRentAreaEixst = 0 ;
			countRentAreaEixst = (int) values.stream().filter(item -> rentAreaEntity.getValue() == Integer.parseInt(item)).count();
		
			if(countRentAreaEixst == 0)// neesu trong db mà kh có value bằng bên ngoài gửi về  thì thêm vào ds xóa
			{
				
				results.add(rentAreaEntity); 
			}
		}
		return results;
	}
	
	
	public List<RentAreaEntity> convertToEntityUpdate( BuildingEntity entity , BuildingDTO buildingDTO)
	{
		List<String> rentAreas = Arrays.asList(buildingDTO.getRentArea().split(",")); // tasch chuỗi thành list
		List<RentAreaEntity> results = new ArrayList<>();
		List<RentAreaEntity> oldRentAreaEntitys = rentAreaRepository.findByBuildingId(entity.getId());
		
		for(String item : rentAreas)
		{
			int countRentAreaEixst = 0 ;
			countRentAreaEixst = (int) oldRentAreaEntitys.stream().filter(oldItem -> oldItem.getValue() == Integer.parseInt(item)).count();
			
			if(countRentAreaEixst == 0)
			{
				RentAreaEntity rentAreaEntity = new RentAreaEntity(); // khởi tạo mỗi lần lặp
				rentAreaEntity.setValue(Integer.parseInt(item));
				rentAreaEntity.setBuilding(entity);
				results.add(rentAreaEntity); // tại sao value lại thay đổi về cùng 1 giá trị ? vì mỗi lần lặp cần khởi tạo lại
			}
		
		}

		return results;
	}
	
	
	public  void validateRentAreas(List<String> values) throws FieldNotFoundException
	{
		for(int i = 0 ; i < values.size() ; i ++) {
			for(int j = i+1 ; j <= values.size() ; j ++ ) {
				if(values.get(i) == values.get(j)) {
					throw new FieldNotFoundException("RentAreaa cannot match!");
				}
			}
		}
	}
	

	
}
