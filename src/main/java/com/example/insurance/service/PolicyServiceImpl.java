package com.example.insurance.service;

import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.example.insurance.dto.PolicyDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.Transaction;
import com.example.insurance.entity.User;
import com.example.insurance.exceptions.ResourceNotFoundException;
import com.example.insurance.mapper.PolicyMapper;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UserRepository;
 
import lombok.AllArgsConstructor;
 
@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {
 
	PolicyRepository policyRepo;
	UserRepository userRepo;
	PolicyMapper policyMapper;
 
	@Override
	public PolicyDTO savePolicy(int userId, PolicyDTO policyDTO) {
		User user = userRepo.findById(userId).get();
		Policy policy = policyMapper.convertFromDTO(policyDTO);
		user.addPolicy(policy);
		policy.addUsers(user);
		return policyMapper.convertToDTO(policyRepo.save(policy));
	}
 
	@Override
	public boolean deletePolicy(int id) {
		// Find the policy entity by policy ID
		Policy policy = policyRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Policy not found with ID: " + id));
		// If policy entity is found, delete it\
		
		/*To delete Policy First User,Payment,Transaction mapping to that policy 
		has to be deleted*/
		
		List<User> users=policy.getUser();
		for(User u:users) {
			u.removePolicy(policy);
		}
		
	
		
		List<Payment> payments=policy.getPayments();
		for(Payment p:payments) {
			p.removePolicy(policy);
		}
		
		List<Transaction> transactions=policy.getTransactions();
		for(Transaction t:transactions) {
			t.removePolicy(policy);
		}
		
		List<Beneficiary> beneficiaries=policy.getBeneficiaries();
		for(Beneficiary b:beneficiaries) {
			b.removePolicy(policy);
		}
		policyRepo.delete(policy);
		return true;
	}
	@Override
	public PolicyDTO updatePolicy(int id,PolicyDTO policyDTO) {
		
		//Find the existing policy to be updated
		Policy existingPolicy=policyRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Policy not found with ID: " + policyDTO.getPolicyId()));
		
		existingPolicy.setCoverageAmount(policyDTO.getCoverageAmount());
		existingPolicy.setDuration(policyDTO.getDuration());
		existingPolicy.setStartDate(policyDTO.getStartDate());
		existingPolicy.setEndDate(policyDTO.getEndDate());
		existingPolicy.setPremium(policyDTO.getPremium());
 
		// Save the updated user
		policyRepo.save(existingPolicy);
 
		// Map updated User entity back to UserDTO
		return policyMapper.convertToDTO(existingPolicy);
	}
 
	@Override
	public PolicyDTO getByPolicyId(int id) 
	{
		
		// Retrieve the policy from the repository by its ID
		Policy policy = policyRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Policy not found with ID: " + id));
 
		// Map the Policy entity to a PolicyDTO
		PolicyDTO policyDTO = policyMapper.convertToDTO(policy); 
 
		return policyDTO;
	}
 
	@Override
	public List<PolicyDTO> getByUserId(int userId) 
	{
		
		// Retrieve policies associated with the user ID from the repository
		User user = userRepo.findById(userId).get();
		List<Policy> policies = user.getPolicies();
		
		// Map the list of Policy entities to a list of PolicyDTO
		List<PolicyDTO> policyDTOs = policies.stream()
				            .map(p -> policyMapper.convertToDTO(p)) 
				            .toList();
 
		return policyDTOs;
	}
	
	
	@Override
	public List<PolicyDTO> viewAllPolicyDetails()
	{
		//Get details of all policies 
		List<Policy> policies=policyRepo.findAll();
		
		// Map the list of Policy entities to a list of PolicyDTO
		return  policies.stream()
				.map(policyMapper::convertToDTO)
				.collect(Collectors.toList());
		
	}
	
	@Override
	public List<PolicyDTO> getByPremium(int premium) {
		
	    // Retrieve the policies from the repository by their premium
	    List<Policy> policies = policyRepo.findByPremium(premium);
	    if(policies.isEmpty()) {
	    	throw new ResourceNotFoundException("Policies not found with premium: " + premium);
	    }
	            
	    // Map the list of Policy entities to a list of PolicyDTO
	    List<PolicyDTO> policyDTOs = policies.stream()
	            .map(policyMapper::convertToDTO) 
	            .collect(Collectors.toList());
 
	    return policyDTOs;
	}
 
 
}
