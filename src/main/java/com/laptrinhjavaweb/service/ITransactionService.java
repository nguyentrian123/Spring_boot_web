package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.TransactionDTO;

public interface ITransactionService {
	List<TransactionDTO> findTransactionsByCustomerId(Long id);
	TransactionDTO save(TransactionDTO transactionDTO);
}
