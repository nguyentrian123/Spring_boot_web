package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.dto.BuildingEdit;
import com.laptrinhjavaweb.exception.FieldNotFoundException;

public class VaidationUtils {
	
	public static void Validate(BuildingEdit buildingEdit) throws FieldNotFoundException
	{
	
		if( buildingEdit.getName() == null || buildingEdit.getName() == "")
		{
			throw new FieldNotFoundException("Name is required");
		}
	}

}
