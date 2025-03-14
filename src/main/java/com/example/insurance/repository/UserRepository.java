package com.example.insurance.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

	Optional<User> findByUserName(String userName);
	
	
} 
