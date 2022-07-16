package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingEdit;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;

@Component
public class RentAreaConverter {

	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public List<RentAreaEntity> convertToEntitySave(BuildingEdit edit , BuildingEntity entity)
	{
		List<String> values = Arrays.asList(edit.getRentArea().split(",")); // tasch chuỗi thành list
		List<RentAreaEntity> results = new ArrayList<>();
		
		for(String item : values)
		{
			RentAreaEntity rentAreaEntity = new RentAreaEntity(); // khởi tạo mỗi lần lặp
			rentAreaEntity.setValue(Integer.parseInt(item));
			rentAreaEntity.setBuilding(entity);
			results.add(rentAreaEntity); // tại sao value lại thay đổi về cùng 1 giá trị ?
		}
		

		return results;
	}
	
	public List<RentAreaEntity> convertToEntityDelete(BuildingEdit edit , BuildingEntity entity)
	{
		List<String> values = Arrays.asList(edit.getRentArea().split(",")); // tasch chuỗi thành list
		List<RentAreaEntity> results = new ArrayList<>();
		List<RentAreaEntity> rentAreaEntitys = rentAreaRepository.findByBuildingId(entity.getId());
		
		for(RentAreaEntity item1 : rentAreaEntitys)
		{
			int count = 0 ;
			for(String item : values)
			{
				if(item1.getValue() == Integer.parseInt(item)) 
				{
					count ++;
				}
			}
		
			if(count == 0)// neesu trong db mà kh có value bằng bên ngoài gửi về  thì thêm vào ds xóa
			{
				
				results.add(item1); 
			}
		}
		return results;
	}
	
	public List<RentAreaEntity> convertToEntityUpdate(BuildingEdit edit , BuildingEntity entity)
	{
		List<String> values = Arrays.asList(edit.getRentArea().split(",")); // tasch chuỗi thành list
		List<RentAreaEntity> results = new ArrayList<>();
		List<RentAreaEntity> rentAreaEntitys = rentAreaRepository.findByBuildingId(entity.getId());
		
		
		
		for(String item : values)
		{
			int count = 0 ;
			for(RentAreaEntity item1 : rentAreaEntitys)
			{
				if(item1.getValue() == Integer.parseInt(item))
				{
					count ++;
				}
			}
			
			if(count == 0)
			{
				RentAreaEntity rentAreaEntity = new RentAreaEntity(); // khởi tạo mỗi lần lặp
				rentAreaEntity.setValue(Integer.parseInt(item));
				rentAreaEntity.setBuilding(entity);
				results.add(rentAreaEntity); // tại sao value lại thay đổi về cùng 1 giá trị ? vì mỗi lần lặp cần khởi tạo lại
			}
		
		}
		
	

		return results;
	}
	
}
