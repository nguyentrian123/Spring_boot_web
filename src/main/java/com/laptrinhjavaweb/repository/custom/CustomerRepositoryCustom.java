package com.laptrinhjavaweb.repository.custom;

import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

public interface CustomerRepositoryCustom {
	List<CustomerEntity> searchCustomer(CustomerDTO customerDTO);
}
