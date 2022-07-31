package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Entity;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEdit;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.entity.BaseEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepositoryCustom;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{

	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingRepositoryCustom buildingRepositoryCustom;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private RentAreaConverter rentAreaConverter;
	
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
	public BuildingDTO save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuilding);
		return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
		
	}
	
	@Override
	@Transactional
	public BuildingEdit saveBuilding(BuildingEdit buildingEdit) {	
		BuildingEntity buildingEntity = new BuildingEntity();		
	
		if(buildingEdit.getId() != null)
		{
			BuildingEntity oldBuilding = buildingRepository.findById(buildingEdit.getId()).get();
			
			
			buildingEntity = buildingConverter.convertToEntity(oldBuilding, buildingEdit);
			buildingEntity.setUsers(oldBuilding.getUsers()); // gửi lại các nv đang quản lý tòa nhà đó
			if(buildingEdit.getRentArea() != null && buildingEdit.getRentArea() != "")
			{
				rentAreaRepository.deleteAll(rentAreaConverter.convertToEntityDelete(buildingEdit, buildingEntity));
				rentAreaRepository.saveAll(rentAreaConverter.convertToEntityUpdate(buildingEdit, buildingEntity));
			}
			
		}
		else
		{
			buildingEntity = buildingConverter.convertToEntity(buildingEdit);
			if(buildingEdit.getRentArea() != null && buildingEdit.getRentArea() != "")
			{
				rentAreaRepository.saveAll(rentAreaConverter.convertToEntitySave(buildingEdit, buildingEntity));
			}
			
		}
		
		return buildingConverter.convertToEdit(buildingRepository.save(buildingEntity));
		
		
	}
	
	
	
	@Override
	@Transactional
	public void assignmentBuilding(BuildingAssignmentRequest bRequest) {
		List<UserEntity> userEntities = userRepository.findByStatusAndRoles_CodeAndBuildings_Id(1, "USER", bRequest.getBuildingId());
		BuildingEntity buildingEntity = buildingRepository.findById(bRequest.getBuildingId()).get();
		
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		
		
		// staffs gửi về mà chưa có trong assigmentbuilding thì adđ vào
		for(Long item : bRequest.getStaffs())
		{
			long count = 0 ; 
			/*int count = 0 ; 
			for(UserEntity entity : userEntities)
			{
				if(entity.getId() == item)
				{
					count ++;
				}
			}*/
			
			count =  userEntities.stream().filter(entity -> entity.getId() == item).count();
	
		
			
			if(count == 0)
			{
				// thêm tòa nhà cho nvql
				UserEntity userEntity = userRepository.findById(item).get();
				buildingEntity.getUsers().add(userEntity);// save manytomany
				buildingEntities.add(buildingEntity);
				
				
			}
			
		}
		
		// ds trả ra mà không có trong staffs gửi về thì remove
		for(UserEntity iUserEntity : userEntities)
		{
			
			int countStaff = 0 ;
			for(Long iLong : bRequest.getStaffs())
			{
				if(iUserEntity.getId() == iLong)
				{
					countStaff ++;
				}
			}
			
			if (countStaff == 0) {

                UserEntity userEntity = userRepository.findById(iUserEntity.getId()).get();
				buildingEntity.getUsers().remove(userEntity);// delete manytomany
				buildingEntities.add(buildingEntity);
				

			}
		}
		
		buildingRepository.saveAll(buildingEntities);
		
		
	}
	
	
	@Override
	public List<BuildingDTO> findBuilding(SearchDTO searchDTO ) {
		List<BuildingDTO> buildingDTOs = new ArrayList<>();
		List<BuildingEntity> entities = buildingRepositoryCustom.searchBuilding(searchDTO);
		
		Map<String,String> districtMap = getDistricts();
		
		for(BuildingEntity  item : entities)
		{

			BuildingDTO buildingDTO = buildingConverter.convertToResponse(item);
			
			for(Map.Entry<String,String> entry : districtMap.entrySet())
			{
				if(item.getDistrictCode().equals(entry.getKey()))
				{
					buildingDTO.setAddress(item.getStreet() +","+ item.getWard() +","+ entry.getValue());
				}
			}
			
			buildingDTOs.add(buildingDTO);
		}
		
		return buildingDTOs;
	}

	
	
	@Override
	public int getTotalItem(SearchDTO searchDTO) {
		
		return (int ) buildingRepository.count();
	}
	
	

	@Override
	public BuildingEdit findById(Long id) {

		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		BuildingEdit buildingEdit = buildingConverter.convertToEdit(buildingEntity);
		
		return buildingEdit;
	}
	

	@Override
	public void deleteBuilding(Long[] ids) {	
		
		for (Long i : ids) {
			buildingRepository.deleteById(i);
			
		}
	}

	

	
}
