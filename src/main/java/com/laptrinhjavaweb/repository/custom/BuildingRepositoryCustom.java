package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {

	//List<BuildingEntity> searchBuilding(SearchDTO searchDTO);
	List<BuildingEntity> searchBuilding(BuildingSearchBuilder builder);
}
