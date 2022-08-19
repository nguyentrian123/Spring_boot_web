package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
public class BuildingRepositoryCustomIMPL  implements BuildingRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BuildingEntity> searchBuilding(SearchDTO searchDTO)
	{
		StringBuilder finalQuery = new StringBuilder();
		
		finalQuery.append("SELECT * ") 
			.append("\nFROM building b");
		finalQuery = buildQueryWithJoin(searchDTO, finalQuery);
		finalQuery.append(SystemConstant.one_equal_one);
		finalQuery = buildQueryCommon(searchDTO,finalQuery);
		finalQuery = buildQuerySpecial(searchDTO, finalQuery);		
        finalQuery.append("\nGROUP BY b.id");
        
        javax.persistence.Query query = entityManager.createNativeQuery(finalQuery.toString(),BuildingEntity.class); // chuyển nó về kiểu BuildingEntity
     
        List<BuildingEntity> entities =  query.getResultList(); // giải quyết bài toán chuyển đổi từ rerult ra
        return   entities;
	}
	
	
	private StringBuilder buildQueryCommon(SearchDTO searchDTO , StringBuilder sql)
	{
		// sdung java reflective lấy  cac field in a class
		Field[] fields = searchDTO.getClass().getDeclaredFields();
		
		 for (Field field : fields)
		 {
			
			 field.setAccessible(true);
			 try {
				
				 if(!field.getName().toLowerCase().equals("renttype") && !field.getName().equals("staff")
						 &&!field.getName().toLowerCase().startsWith("rentprice") && !field.getName().toLowerCase().startsWith("rentarea")
						 && !field.getName().equals("district"))
				 {
					 if(field.get(searchDTO) != null)
					 {
						 String value =   String.valueOf(field.get(searchDTO));
					
						 if(  field.getType().equals(String.class) && !StringUtils.isNull(value))
						 {		
							 sql.append("\nAND b."+field.getName().toLowerCase()+" LIKE '%")
							 .append(value+"%'");
							 
						 }
						 else if(!StringUtils.isNull(value)) {	
							 sql.append("\nAND b."+field.getName() +" = ")
							 .append(value);
						 }
					 }
				
				 }
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 
			
		 }
		  
		
		return sql;
	}
	
	private StringBuilder buildQuerySpecial(SearchDTO searchDTO, StringBuilder sql)
	{
		// sử dụng model để đưa key và value vào thì các key có KDL là số thì tự check rỗng = null luôn
	

		Integer rentPriceTo = searchDTO.getRentPriceTo();
		Integer rentPriceFrom = searchDTO.getRentPriceFrom();
		String districtCode = searchDTO.getDistrict();
		List<String> types = searchDTO.getRenttype();
		Long staffId = searchDTO.getStaff();
		Integer rentAreaFrom = searchDTO.getRentAreaFrom();
		Integer rentAreaTo = searchDTO.getRentAreaTo();
			
			
		
			if (null != staffId ) { 
	            sql.append("\nAND u.id  = ")
	                    .append(staffId);
	        }
		
			if( !StringUtils.isNull(districtCode)) {
				sql.append("\nAND b.districtcode = '")
                .append(districtCode + "'");
			}
	        
	        if (null != rentPriceFrom ) {
	        	sql.append("\nAND b.rentprice >= ")
	                    .append(rentPriceFrom);
	        }
	        
	        if (null != rentPriceTo) {
	        	sql.append("\nAND b.rentprice <=")
	                    .append(rentPriceTo);
	        }
	        
	        // khoong can join rentarea
		 	if(rentAreaFrom != null || rentAreaTo != null)
		 	{
		 		sql.append("and EXISTS( select value from rentarea ra where b.id = ra.buildingid ");
		 		if(rentAreaFrom != null)
			 	{
		 			sql.append(" and ra.value >= " + rentAreaFrom + "");
			 	}
			 	if(rentAreaTo != null)
			 	{
			 		sql.append(" and ra.value <= " + rentAreaTo + "");
			 	}
		 		
			 	sql.append(")");
		 	}
	        
	       
	     
	        if(null != types && types.size() > 0)
	        {
	        	sql.append(" and (");
		 		//.filter(item -> item != "") nếu muốn ktra ddkien trong vòng for
		 		String sqlJoin = types.stream().map(item -> " b.type LIKE'%"+item+"%'").collect(Collectors.joining(" or "));
		 		
		 		sql.append(sqlJoin);
		 		sql.append(")");
	        	
	        }   
	        
	    return sql;    
	}
	
	private StringBuilder buildQueryWithJoin(SearchDTO searchDTO, StringBuilder sql)
	{
		
		Long staffId = searchDTO.getStaff();
		
		
		if (null != staffId ) {
			sql.append("\n JOIN assignmentbuilding ab on ab.buildingid = b.id  JOIN users u on u.id = ab.staffid");
         
        }
        
        
        return sql;
	}

}

