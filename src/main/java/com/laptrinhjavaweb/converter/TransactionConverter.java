package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;

@Component
public class TransactionConverter {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	CustomerRepository customerRepository;
	
	public TransactionDTO converToDTO(TransactionEntity transactionEntity)
	{
		TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
		return transactionDTO;
	}
	
	public TransactionEntity convertToEntity(TransactionDTO transactionDTO)
	{
		TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
		CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
		transactionEntity.setCustomer(customerEntity);
		return transactionEntity;
	}
}
