package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.exception.FieldNotFoundException;

public class VaidationUtils {
	
	public static void Validate(BuildingDTO buildingDTO) throws FieldNotFoundException
	{
	
		if( buildingDTO.getName() == null || buildingDTO.getName() == "")
		{
			throw new FieldNotFoundException("Name is required");
		}
	}

}
