package com.example.insurance.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer>{
	
	
}
