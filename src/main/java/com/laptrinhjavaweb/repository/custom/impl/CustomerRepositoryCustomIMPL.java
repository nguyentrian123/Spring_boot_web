package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
public class CustomerRepositoryCustomIMPL implements CustomerRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CustomerEntity> searchCustomer(CustomerDTO customerDTO) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM customer c");
		sql = buildSqlJoin(customerDTO, sql);
		sql.append(SystemConstant.one_equal_one);
		sql = buildSqlCommon(customerDTO, sql);
		sql = buildQuerySpecial(customerDTO, sql);
		sql.append("\nGROUP BY c.id");
		
		javax.persistence.Query query = entityManager.createNativeQuery(sql.toString(),CustomerEntity.class); // chuyển nó về kiểu BuildingEntity
	     
        List<CustomerEntity> entities =  query.getResultList();
		
		return entities;
	}
	
	private StringBuilder buildSqlCommon(CustomerDTO customerDTO, StringBuilder sql)
	{
		Field[] fields = customerDTO.getClass().getDeclaredFields();
		
		for(Field item : fields)
		{
			item.setAccessible(true);
			
			try {
				if(!item.getName().equals("staff")) {
					
					if(item.get(customerDTO) != null) {
						 String value =   String.valueOf(item.get(customerDTO));
						 

						 if(  item.getType().equals(String.class) && !StringUtils.isNull(value))
						 {		
							 sql.append("\nAND c."+item.getName().toLowerCase()+" LIKE '%")
							 .append(value+"%'");
							 
						 }
						 else if(!StringUtils.isNull(value)) {	
							 sql.append("\nAND c."+item.getName() +" = ")
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
	
	private StringBuilder buildQuerySpecial(CustomerDTO customerDTO, StringBuilder sql)
	{
		Long staffId = customerDTO.getStaff();
		
		if (null != staffId ) { 
            sql.append("\nAND u.id  = ")
                    .append(staffId);
        }
		
		return sql;
	}
	
	private StringBuilder buildSqlJoin(CustomerDTO customerDTO , StringBuilder sql) {
		
		Long staffId = customerDTO.getStaff();
		
		if (null != staffId ) {
			sql.append("\n JOIN assignmentcustomer ac on ac.customer_id = c.id  JOIN users u on u.id = ac.user_id");
         
        }
		
		return sql;
	}


}
