package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

@Component
public class CustomerConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public CustomerDTO converToDTO(CustomerEntity customerEntity)
	{
		CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
		return customerDTO;
	}
}
