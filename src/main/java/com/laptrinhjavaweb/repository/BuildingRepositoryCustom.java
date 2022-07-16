package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {

	List<BuildingEntity> searchBuilding(SearchDTO searchDTO);
}
