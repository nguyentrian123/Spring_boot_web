package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long>{
	List<TransactionEntity> findByCustomer_Id(Long id);
}
