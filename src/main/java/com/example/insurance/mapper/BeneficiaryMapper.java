package com.example.insurance.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;


import com.example.insurance.dto.BeneficiaryDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.User;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UserRepository;

import lombok.AllArgsConstructor;
 
@Component
@AllArgsConstructor
public class BeneficiaryMapper {
	
	UserRepository userRepo;
	PolicyRepository policyRepo;
	
	public Beneficiary convertFromDTO(BeneficiaryDTO dto) 
	{
		
        Beneficiary beneficiary = new Beneficiary();
        
        beneficiary.setBeneficiaryId(dto.getBeneficiaryId());
        
        Optional<User> user0 = userRepo.findById(dto.getUserId());        
        beneficiary.setUser(user0.get());
        
        Optional<Policy> policy0=policyRepo.findById(dto.getPolicyId());
        beneficiary.setPolicies(policy0.get());
        
        beneficiary.setName(dto.getName());
        beneficiary.setRelationship(dto.getRelationship());
        
        return beneficiary;
    }
 
    public BeneficiaryDTO convertToDTO(Beneficiary beneficiary) 
    {
        BeneficiaryDTO dto = new BeneficiaryDTO();
        
        dto.setBeneficiaryId(beneficiary.getBeneficiaryId());
        dto.setUserId(beneficiary.getUser().getUserId());
        dto.setPolicyId(beneficiary.getPolicies().getPolicyId());
        dto.setName(beneficiary.getName());
        dto.setRelationship(beneficiary.getRelationship());
        
        return dto;
    }
}
