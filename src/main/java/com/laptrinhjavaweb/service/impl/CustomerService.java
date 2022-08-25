package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerAssignmentRequest;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerRepositoryCustom customerRepositoryCustom;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	TransactionConverter transactionConverter;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomerConverter customerConverter;

	@Override
	public Map<String, String> getTransactionType() {
		Map<String,String> transactionTypes = new HashMap<String, String>();	
		for(TransactionTypeEnum item : TransactionTypeEnum.values()) {
			transactionTypes.put(item.toString(), item.getTransactionTypeValue());
		}	
		return transactionTypes;
	}

	@Override
	public List<CustomerDTO> searchCustomer(CustomerDTO customerDTO) {
		List<CustomerEntity> entities = customerRepositoryCustom.searchCustomer(customerDTO);
		List<CustomerDTO> rerults = new ArrayList<>();
		
		
		for(CustomerEntity item : entities) {	
			CustomerDTO customer = customerConverter.converToDTO(item);
			List<UserEntity> userEntities = userRepository.findByStatusAndRoles_CodeAndCustomers_Id(1, "USER", item.getId());
			List<String> nameStaffs = new ArrayList<>();
			
			for(UserEntity entity : userEntities) {
				nameStaffs.add(entity.getFullName());
			}
			customer.setFullNameStaff(String.join(",", nameStaffs));
			rerults.add(customer);
		}
		
		
		return rerults;
	}

	@Override
	public CustomerDTO findById(Long id) {
		CustomerEntity customerEntity = customerRepository.findById(id).get();
		CustomerDTO customerDTO = customerConverter.converToDTO(customerEntity);

		return customerDTO;
	}
	
	@Override
	@Transactional
	public CustomerDTO save(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = new CustomerEntity();
		
		if(customerDTO.getId() != null) {
			CustomerEntity oldCustomer = customerRepository.findById(customerDTO.getId()).get();
			customerEntity = customerConverter.convertToEntity(oldCustomer , customerDTO);
			customerEntity.setUsers(oldCustomer.getUsers());
			customerEntity.setTransactions(oldCustomer.getTransactions());
		}
		else {
			customerEntity = customerConverter.convertToEntity(customerDTO);
		}
		return customerConverter.converToDTO(customerRepository.save(customerEntity));
	}
	

	@Override
	@Transactional
	public void assignmentCustomer(CustomerAssignmentRequest customerAssignmentRequest) {
		List<UserEntity> userEntities = userRepository.findByStatusAndRoles_CodeAndCustomers_Id(1, "USER", customerAssignmentRequest.getCustomerId());
		CustomerEntity customerEntity = customerRepository.findById(customerAssignmentRequest.getCustomerId()).get();
		
		for(Long item :  customerAssignmentRequest.getStaffs()) {
			int countStaffExist = 0 ;
			countStaffExist = (int) userEntities.stream().filter(userEntity -> userEntity.getId() == item).count();
			
			if(countStaffExist == 0 ) {
				UserEntity userEntity = userRepository.findById(item).get();
				customerEntity.getUsers().add(userEntity);
			}	
		}
		
		for(UserEntity item : userEntities) {
			int countStaffExist = 0 ;
			countStaffExist = (int) customerAssignmentRequest.getStaffs().stream().filter(staffId -> staffId == item.getId()).count();
			
			if(countStaffExist == 0) {
				customerEntity.getUsers().remove(item);
			}
		}
		
		customerRepository.save(customerEntity);
	}

	

	
	
	

	
}
