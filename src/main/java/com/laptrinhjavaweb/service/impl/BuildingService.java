package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.service.IBuildingService;


@Service
public class BuildingService implements IBuildingService{

	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item: DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item: BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypeValue());
		}
		return buildingTypes;
	}

	
	@Override
	@Transactional
	public BuildingDTO saveBuilding(BuildingDTO buildingDTO) {	
		BuildingEntity buildingEntity = new BuildingEntity();		
	
		if(buildingDTO.getId() != null)
		{
			BuildingEntity oldBuilding = buildingRepository.findById(buildingDTO.getId()).get();
			buildingEntity = buildingConverter.convertToEntity(oldBuilding, buildingDTO);
			buildingEntity.setUsers(oldBuilding.getUsers()); // gửi lại các nv đang quản lý tòa nhà đó
		}
		else
		{
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		}
		
		return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
	}
	
	
	
	@Override
	@Transactional
	public void assignmentBuilding(BuildingAssignmentRequest buildingAssignmentRequest) {
		List<UserEntity> userEntities = userRepository.findByStatusAndRoles_CodeAndBuildings_Id(1, "USER", buildingAssignmentRequest.getBuildingId());
		BuildingEntity buildingEntity = buildingRepository.findById(buildingAssignmentRequest.getBuildingId()).get();		
		
		// staffs gửi về mà chưa có trong assigmentbuilding thì adđ vào
		for(Long staffId : buildingAssignmentRequest.getStaffs())
		{
			int countStaffsExist = 0 ; 
			countStaffsExist =  (int) userEntities.stream().filter(entity -> entity.getId() == staffId).count();
			
			if(countStaffsExist == 0)
			{
				// thêm tòa nhà cho nvql
				UserEntity userEntity = userRepository.findById(staffId).get();
				buildingEntity.getUsers().add(userEntity);// save manytomany
				
			}	
			
		}	
		
		// ds trả ra mà không có trong staffs của buildingAssignmentRequest gửi về thì remove
		for(UserEntity entity : userEntities)
		{			
			int countStaffsExist = 0;
			countStaffsExist = (int) buildingAssignmentRequest.getStaffs().stream().filter(staffId ->  staffId == entity.getId()).count();
			
			if (countStaffsExist == 0) {
				buildingEntity.getUsers().remove(entity);// delete manytomany
			}
		}

		buildingRepository.save(buildingEntity);	
	}
	
	
	@Override
	public List<BuildingDTO> findBuilding(SearchDTO searchDTO ) {
		List<BuildingDTO> buildingDTOs = new ArrayList<>();
		BuildingSearchBuilder builder = convertBuildingSearchBuilder(searchDTO);
		List<BuildingEntity> entities = buildingRepository.searchBuilding(builder);
		Map<String,String> districtMap = getDistricts();
		
		for(BuildingEntity  item : entities)
		{
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(item);
			buildingDTO.setAddress(districtMap.entrySet().stream().filter(entry -> item.getDistrictCode().equals(entry.getKey()))
																  .map(entry -> item.getStreet() +","+ item.getWard() +","+  entry.getValue())
																  .collect(Collectors.joining())); // đưa về String, biết là chỉ có 1 districtCode nhưng vì lặp 
																								// nên cần phải collect.
			buildingDTOs.add(buildingDTO);
		}
		return buildingDTOs;
	}

	
	private BuildingSearchBuilder convertBuildingSearchBuilder(SearchDTO searchDTO) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
											.setName(searchDTO.getName())
											.setStreet(searchDTO.getStreet())
											.setWard(searchDTO.getWard())
											.setDistrict(searchDTO.getDistrict())
											.setFloorArea(searchDTO.getFloorArea())
											.setNumberOfBasement(searchDTO.getNumberOfBasement())
											.setDirection(searchDTO.getDirection())
											.setLevel(searchDTO.getLevel())
											.setRentPriceFrom(searchDTO.getRentPriceFrom())
											.setRentPriceTo(searchDTO.getRentPriceTo())
											.setRentAreaFrom(searchDTO.getRentAreaFrom())
											.setRentAreaTo(searchDTO.getRentAreaTo())
											.setStaff(searchDTO.getStaff())
											.setRenttype(searchDTO.getRenttype())
											.build();
		
		return builder;
	}
	

	@Override
	public int getTotalItem(SearchDTO searchDTO) {
		return (int ) buildingRepository.count();
	}
	
	

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
		return buildingDTO;
	}
	

	@Override
	@Transactional
	public void deleteBuilding(Long[] ids) {	
		for (Long i : ids) {
			buildingRepository.deleteById(i);
		}
	}

	

	
}
