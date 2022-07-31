package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

public interface CustomerRepositoryCustom {

	List<CustomerEntity> searchCustomer(CustomerDTO customerDTO);
}
