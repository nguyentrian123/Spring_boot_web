package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.request.SearchDTO;

public interface IBuildingService {

	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO saveBuilding(BuildingDTO buildingDTO);
	List<BuildingDTO> findBuilding(SearchDTO searchDTO );
	int getTotalItem(SearchDTO searchDTO);
	BuildingDTO findById(Long id);
	void assignmentBuilding(BuildingAssignmentRequest bRequest);
	void deleteBuilding(Long[] ids);
	
}
