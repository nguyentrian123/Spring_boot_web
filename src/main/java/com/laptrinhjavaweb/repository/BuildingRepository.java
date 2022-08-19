package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>  {
	

}
