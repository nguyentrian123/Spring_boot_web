package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

}
