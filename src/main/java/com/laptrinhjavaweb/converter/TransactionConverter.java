package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;

@Component
public class TransactionConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public TransactionDTO converToDTO(TransactionEntity transactionEntity)
	{
		TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
		return transactionDTO;
	}
}
