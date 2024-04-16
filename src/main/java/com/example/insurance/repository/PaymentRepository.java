package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.insurance.entity.Payment;



public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
}
