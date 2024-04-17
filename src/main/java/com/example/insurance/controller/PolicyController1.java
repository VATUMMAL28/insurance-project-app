package com.example.insurance.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.service.PolicyService1;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/insurance/policies1")
public class PolicyController1 {

	PolicyService1 policyService1;
	
	@PostMapping("/save")
	public ResponseEntity<PolicyDTO> savePolicy(@RequestParam List<Integer> userIds,@RequestBody PolicyDTO policyDTO ) {

        PolicyDTO savedPolicy = policyService1.savePolicy(userIds, policyDTO);

        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }
	
	// EndPoint to calculate policy revenue
    @GetMapping("/calculate-revenue")
    public Map<Integer, Double> calculatePolicyRevenue() {
        return policyService1.calculatePolicyRevenue();
    }
    
    // EndPoint to get policy sales
    @GetMapping("/sales")
    public Map<Integer, Integer> getPolicySales() {
        return policyService1.getPolicySales();
    }
    
    // EndPoint to get maximum policy revenue and its ID as entry
    @GetMapping("/max-revenue-and-id")
    public Entry<Integer, Double> getMaxPolicyRevenueAndIdAsEntry() {
        return policyService1.getMaxPolicyRevenueAndIdAsEntry();
    }
    
    // EndPoint to get minimum policy revenue and its ID as entry
    @GetMapping("/min-revenue-and-id")
    public Entry<Integer, Double> getMinPolicyRevenueAndIdAsEntry() {
        return policyService1.getMinPolicyRevenueAndIdAsEntry();
    }
    
    // EndPoint to get policies with maximum sales
    @GetMapping("/max-sales")
    public Map<Integer, Integer> getMaxPolicySales() {
        return policyService1.getMaxPolicySales();
    }
    
    // EndPoint to get policies with minimum sales
    @GetMapping("/min-sales")
    public Map<Integer, Integer> getMinPolicySales() {
        return policyService1.getMinPolicySales();
    }
    
    // EndPoint to calculate average revenue
    @GetMapping("/average-revenue")
    public double getAverageRevenue() {
        return policyService1.getAverageRevenue();
    }
    
    // EndPoint to calculate average sales
    @GetMapping("/average-sales")
    public double getAverageSales() {
        return policyService1.getAverageSales();
    }
}