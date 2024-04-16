package com.example.insurance.service;

import java.util.List; 
import org.springframework.stereotype.Service;
import com.example.insurance.dto.PolicyDTO;
 
@Service
public interface PolicyService {
 
	PolicyDTO savePolicy(int userId, PolicyDTO policyDTO);
 
	boolean deletePolicy(int id);
 
	PolicyDTO updatePolicy(int id, PolicyDTO policyDTO);
 
	PolicyDTO getByPolicyId(int id);
 
	List<PolicyDTO> getByUserId(int userId);
	
	List<PolicyDTO> viewAllPolicyDetails();
	
	List<PolicyDTO> getByPremium(int premium);
}