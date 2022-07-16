package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>  {
	

}
