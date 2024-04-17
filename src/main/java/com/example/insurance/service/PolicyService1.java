package com.example.insurance.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import com.example.insurance.dto.PolicyDTO;

@Service
public interface PolicyService1 {
	
	PolicyDTO savePolicy(List<Integer> userIds, PolicyDTO policyDTO);
	
	Map<Integer, Double> calculatePolicyRevenue();
	
	Map<Integer, Integer> getPolicySales();
	
	Map.Entry<Integer, Double> getMaxPolicyRevenueAndIdAsEntry();
	
	Map.Entry<Integer, Double> getMinPolicyRevenueAndIdAsEntry();
	
	Map<Integer, Integer> getMaxPolicySales();
	
	Map<Integer, Integer> getMinPolicySales();
	
	double getAverageRevenue();
	
	double getAverageSales();
}
