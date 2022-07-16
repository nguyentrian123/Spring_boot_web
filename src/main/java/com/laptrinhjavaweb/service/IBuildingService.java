package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEdit;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;

public interface IBuildingService {

	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO save(BuildingDTO newBuilding);
	BuildingEdit saveBuilding(BuildingEdit buildingEdit);
	List<BuildingDTO> findBuilding(SearchDTO searchDTO );
	int getTotalItem(SearchDTO searchDTO);
	BuildingEdit findById(Long id);
	void assignmentBuilding(BuildingAssignmentRequest bRequest);
	void deleteBuilding(Long[] ids);
	
}
