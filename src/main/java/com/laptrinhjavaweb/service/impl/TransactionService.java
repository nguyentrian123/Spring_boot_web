package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService{

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	TransactionConverter transactionConverter;
	
	@Override
	public List<TransactionDTO> findTransactionsByCustomerId(Long id) {
		List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_Id(id);
		List<TransactionDTO> transactionDTOs = new ArrayList<>();
		
		for(TransactionEntity item : transactionEntities) {
			TransactionDTO transactionDTO = transactionConverter.converToDTO(item);
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
		
	}

	@Override
	@Transactional
	public TransactionDTO save(TransactionDTO transactionDTO) {
		TransactionEntity transactionEntity = transactionConverter.convertToEntity(transactionDTO);
		return transactionConverter.converToDTO(transactionRepository.save(transactionEntity));
	}

}
