package com.example.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer>{
	List<Policy> findByPremium(int premium);
}
