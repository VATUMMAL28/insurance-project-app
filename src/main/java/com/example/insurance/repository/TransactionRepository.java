package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
}
