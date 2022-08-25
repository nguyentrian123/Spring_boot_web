package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.CustomerAssignmentRequest;

public interface ICustomerService {

	Map<String, String> getTransactionType();
	List<CustomerDTO> searchCustomer(CustomerDTO customerDTO);
	CustomerDTO findById(Long id);
	void assignmentCustomer(CustomerAssignmentRequest customerAssignmentRequest);
	CustomerDTO save(CustomerDTO customerDTO);
	
}
