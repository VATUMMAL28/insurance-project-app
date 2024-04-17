package com.example.insurance.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.User;
import com.example.insurance.mapper.PolicyMapper;
import com.example.insurance.repository.PolicyRepository1;
import com.example.insurance.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PolicyServiceImpl1 implements PolicyService1{
	
	PolicyRepository1 policyRepo1;
	UserRepository userRepo;
	PolicyMapper policyMapper;
	
	public PolicyDTO savePolicy(List<Integer> userIds, PolicyDTO policyDTO) {
	    Policy policy = policyMapper.convertFromDTO(policyDTO);
	    List<User> users = userRepo.findAllById(userIds);
	    
	    for (User user : users) {
	        user.addPolicy(policy);
	        policy.addUsers(user);
	    }
	    
	    return policyMapper.convertToDTO(policyRepo1.save(policy));
	}
	
	public Map<Integer, Double> calculatePolicyRevenue() {
		List<Policy> policies = policyRepo1.findAll();
        Map<Integer, Double> policyRevenueMap = new HashMap<>();
        for (Policy policy : policies) {
            int userCount = policy.getUser().size(); // Get the number of users associated with the policy
            double revenue = userCount * policy.getPremium();
            policyRevenueMap.put(policy.getPolicyId(), revenue);
        }
        return policyRevenueMap;
    }
	
	public Map<Integer, Integer> getPolicySales() {
        List<Policy> policies = policyRepo1.findAll();
        Map<Integer, Integer> policySalesMap = new HashMap<>();
        for (Policy policy : policies) {
            int userCount = policy.getUser().size(); // Get the number of users associated with the policy
            policySalesMap.put(policy.getPolicyId(), userCount);
        }
        return policySalesMap;
    }
	
	//Method to get Maximum policy revenue and its policy ID
	public Map.Entry<Integer, Double> getMaxPolicyRevenueAndIdAsEntry() {
        Map<Integer, Double> policyRevenueMap = calculatePolicyRevenue();
        return policyRevenueMap.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
    }
    //In the same way Method to get minimum policy revenue and its policy ID
	public Map.Entry<Integer, Double> getMinPolicyRevenueAndIdAsEntry() {
        Map<Integer, Double> policyRevenueMap = calculatePolicyRevenue();
        return policyRevenueMap.entrySet().stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .orElse(null);
    }
	
	// Method to get policies with maximum sales
    public Map<Integer, Integer> getMaxPolicySales() {
        Map<Integer, Integer> policySalesMap = getPolicySales();
        int maxSales = policySalesMap.values().stream().max(Integer::compareTo).orElse(0);
        Map<Integer, Integer> maxSalesEntries = new HashMap<>();
        for (Entry<Integer, Integer> entry : policySalesMap.entrySet()) {
            if (entry.getValue() == maxSales) {
                maxSalesEntries.put(entry.getKey(),entry.getValue());
            }
        }
        return maxSalesEntries;
    }
    
    // Method to get policies with minimum sales
    public Map<Integer, Integer> getMinPolicySales() {
        Map<Integer, Integer> policySalesMap = getPolicySales();
        int minSales = policySalesMap.values().stream().min(Integer::compareTo).orElse(0);
        Map<Integer, Integer> minSalesEntries = new HashMap<>();
        for (Entry<Integer, Integer> entry : policySalesMap.entrySet()) {
            if (entry.getValue() == minSales) {
                minSalesEntries.put(entry.getKey(),entry.getValue());
            }
        }
        return minSalesEntries;
    }
    
    // Method to get Average Revenue
    public double getAverageRevenue() 
    {
        Map<Integer, Double> policyRevenueMap = calculatePolicyRevenue();
        
        double totalRevenue = policyRevenueMap.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalRevenue / policyRevenueMap.size();
    }
    
    // Method to get Average Sales
    public double getAverageSales() 
    {
        Map<Integer,Integer> policySalesMap = getPolicySales();
        
        double totalSales = policySalesMap.values().stream().mapToInt(Integer::intValue).sum();
        return totalSales / policySalesMap.size();
    }
    
}
