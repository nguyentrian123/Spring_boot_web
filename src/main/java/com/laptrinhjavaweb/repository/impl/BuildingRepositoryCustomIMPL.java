package com.laptrinhjavaweb.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryCustomIMPL  implements BuildingRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BuildingEntity> searchBuilding(SearchDTO searchDTO)
	{
		StringBuilder finalQuery = new StringBuilder();
		
		finalQuery.append("SELECT * ") //  b.id, b.name, b.street, b.ward ,  b.createddate, b.createdby, b.floorarea,  b.rentprice, b.servicefee, b.brokeragefee, b.managername, b.managerphone
			.append("\nFROM building b");
		
		
		StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();
		
        buildQueryWithJoin(searchDTO, joinQuery, whereQuery);
        buildQueryWithoutJoin(searchDTO, whereQuery);
        
        finalQuery.append(joinQuery)
        .append("\nWHERE 1 = 1")
        .append(whereQuery)
        .append("\nGROUP BY b.id");
        
		
        
        javax.persistence.Query query = entityManager.createNativeQuery(finalQuery.toString(),BuildingEntity.class); // chuyển nó về kiểu BuildingEntity
     
        List<BuildingEntity> entities =  query.getResultList();
        return   entities;
	}
	
	
	private void buildQueryWithoutJoin(SearchDTO searchDTO, StringBuilder whereQuery)
	{
		// sử dụng model để đưa key và value vào thì các key có KDL là số thì tự check rỗng = null luôn
	
		String name = searchDTO.getName();
		String ward = searchDTO.getWard();
		String street = searchDTO.getStreet();
		String direction = searchDTO.getDirection();
		String level = searchDTO.getLevel();
		String managerName = searchDTO.getManagerName();
		String managerPhone  = searchDTO.getManagerPhone();
		Integer numberOfBasement = searchDTO.getNumberOfBasement();
		Integer floorArea = searchDTO.getFloorArea();
		Integer rentPriceTo = searchDTO.getRentPriceTo();
		Integer rentPriceFrom = searchDTO.getRentPriceFrom();
		String districtCode = searchDTO.getDistrict();
		List<String> types = searchDTO.getRenttype();
		
		
		 if (null != name && !"".equals(name)) {
	            whereQuery.append("\nAND b.name LIKE '%")
	                    .append(name)
	                    .append("%'");
	    }
		 	if (null != street && !"".equals(street)) {
	            whereQuery.append("\nAND b.street LIKE '%")
	                    .append(street)
	                    .append("%'");
	        }
	        if (null != ward && !"".equals(ward)) {
	            whereQuery.append("\nAND b.ward LIKE '%")
	                    .append(ward)
	                    .append("%'");
	        }
	        if (null != direction && !"".equals(direction)) {
	            whereQuery.append("\nAND b.direction LIKE '%")
	                    .append(direction)
	                    .append("%'");
	        }
	        if(null != districtCode && !"".equals(districtCode))
	        {
	        	 whereQuery.append("\nAND b.districtcode LIKE '%")
              .append(districtCode)
              .append("%'");
	        }
	        if (null != level && !"".equals(level)) {
	            whereQuery.append("\nAND b.level LIKE '%")
	                    .append(level)
	                    .append("%'");
	        }
	        if (null != managerName && !"".equals(managerName)) {
	            whereQuery.append("\nAND b.managername LIKE '%")
	                    .append(managerName)
	                    .append("%'");
	        }
	        if (null != managerPhone && !"".equals(managerPhone)) {
	            whereQuery.append("\nAND b.managerphone LIKE '%")
	                    .append(managerPhone)
	                    .append("%'");
	        }
	        
	        if (null != rentPriceFrom ) {
	            whereQuery.append("\nAND b.rentprice ")
	                    .append(rentPriceFrom);
	        }
	        
	        if (null != rentPriceTo) {
	            whereQuery.append("\nAND b.rentprice <=")
	                    .append(rentPriceTo);
	        }
	        
	        if (null != rentPriceTo  && null != rentPriceFrom ) {
	            whereQuery.append("\nAND b.rentprice  BETWEEN   ")
	                    .append(rentPriceFrom)
	                    .append("AND")
	                    .append(rentPriceTo);
	            		
	        }
	     
	        
	        if (null != numberOfBasement ) {
	            whereQuery.append("\nAND b.numberofbasement =")
	                    .append(numberOfBasement);
	        }
	        
	        if (null != floorArea ) {
	            whereQuery.append("\nAND b.floorarea = ")
	                    .append(floorArea);
	        }
	        if(null != types && !types.isEmpty())
	        {
	        	
	        	int count = 0 ;
	        	for(String string : types)
	        	{
	        		count ++ ;
	        		if(count > 1) // nếu param lớn hơn 1 thì dùng OR (để tìm kiếm đầy đủ nhất)
	        		{
	        			whereQuery.append("\nOR b.type LIKE '%")
		                 .append(string)
		                 .append("%'");
	        		}
	        		else {
	        			whereQuery.append("\nAND ( b.type LIKE '%")
		                 .append(string)
		                 .append("%'");
	        		} 
	        		
	        	}
	        	whereQuery.append(")");
	        	
	        }      
	}
	
	private void buildQueryWithJoin(SearchDTO searchDTO, StringBuilder joinQuery, StringBuilder whereQuery)
	{
		
		Integer rentAreaFrom = searchDTO.getRentAreaFrom();
		Integer rentAreaTo = searchDTO.getRentAreaTo();
		Integer staffId = searchDTO.getStaff();
		
		
		if (null != staffId ) {
			  
            joinQuery.append("\n JOIN assignmentbuilding ab on ab.buildingid = b.id  JOIN users u on u.id = ab.staffid");
            whereQuery.append("\nAND u.id  = '")
                    .append(staffId)
                    .append("'");
        }
        
        if (null != rentAreaFrom ) {
      	 
            joinQuery.append(" INNER JOIN rentarea ra  on ra.buildingid = b.id" );
            whereQuery.append("\nAND ra.value >= '")
                    .append(rentAreaFrom)
                    .append("'");
        }
        
        if (null != rentAreaTo) {
	      	 
            joinQuery.append(" INNER JOIN rentarea ra  on ra.buildingid = b.id" );
            whereQuery.append("\nAND ra.value <= '")
                    .append(rentAreaTo)
                    .append("'");
        }
        
        
        if (null != rentAreaTo && null != rentAreaFrom ) {
	      	 
            joinQuery.append(" INNER JOIN rentarea ra  on ra.buildingid = b.id" );
            whereQuery.append("\nAND ra.value BETWEEN '")
		            .append(rentAreaFrom)
	                .append("AND")
	                .append(rentAreaTo);
        }
	}

}

