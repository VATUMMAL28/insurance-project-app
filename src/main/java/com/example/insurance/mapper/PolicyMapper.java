package com.example.insurance.mapper;

import org.springframework.stereotype.Component;

import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.entity.Policy;
 
@Component
public class PolicyMapper {
	
	public PolicyDTO convertToDTO(Policy policy) 
	{
		PolicyDTO dto = new PolicyDTO();
        dto.setPolicyId(policy.getPolicyId());
        dto.setPolicyNumber(policy.getPolicyNumber());
        dto.setCoverageAmount(policy.getCoverageAmount());
        dto.setDuration(policy.getDuration());
        dto.setStartDate(policy.getStartDate());
        dto.setEndDate(policy.getEndDate());
        dto.setPremium(policy.getPremium());
        return dto;
    }
	
    public Policy convertFromDTO(PolicyDTO dto) 
    {	
        Policy policy = new Policy();
        policy.setPolicyId(dto.getPolicyId());
        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setDuration(dto.getDuration());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());
        policy.setPremium(dto.getPremium());
        return policy;
    }
}

