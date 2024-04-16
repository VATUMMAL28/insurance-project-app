package com.example.insurance.service;

import com.example.insurance.dto.BeneficiaryDTO;
import com.example.insurance.entity.Beneficiary;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.User;
import com.example.insurance.exceptions.ResourceNotFoundException;
import com.example.insurance.mapper.BeneficiaryMapper;
import com.example.insurance.repository.BeneficiaryRepository;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
@Service
@AllArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {
 
    BeneficiaryRepository beneficiaryRepository;
    UserRepository userRepository;
    PolicyRepository policyRepository;
    BeneficiaryMapper beneficiaryMapper;
 
    @Override
    public BeneficiaryDTO saveBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        Beneficiary beneficiary = beneficiaryMapper.convertFromDTO(beneficiaryDTO);
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.convertToDTO(savedBeneficiary);
    }
 
    @Override
    public boolean deleteBeneficiary(int beneficiaryId) {
    	
    	Beneficiary beneficiary=beneficiaryRepository.findById(beneficiaryId)
    			.orElseThrow(() -> new ResourceNotFoundException("Beneficiary not found with ID: " +beneficiaryId ));
    	
    	User user=beneficiary.getUser();
    	user.removeBeneficiary(beneficiary);
    	
    	Policy policy=beneficiary.getPolicies();
    	policy.removeBeneficiary(beneficiary);
    	
        beneficiaryRepository.delete(beneficiary);
        return true;
    }
 
    @Override
    public BeneficiaryDTO updateBeneficiary(int id,BeneficiaryDTO beneficiaryDTO) 
    {
    	
    	Beneficiary existingBeneficiary=beneficiaryRepository.
    			findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Beneficiary not found with ID: " + beneficiaryDTO.getBeneficiaryId()));
        
    	existingBeneficiary.setName(beneficiaryDTO.getName());
    	existingBeneficiary.setRelationship(beneficiaryDTO.getRelationship());
    	
        Beneficiary updatedBeneficiary = beneficiaryRepository.save(existingBeneficiary);
        return beneficiaryMapper.convertToDTO(updatedBeneficiary);
    }
 
 
    @Override
    public List<BeneficiaryDTO> getByUserId(int userId) {
    	
    	Optional<User> u = userRepository.findById(userId);
    	if (u.isPresent())
		{
			List<Beneficiary> beneficiaries=u.get().getBeneficiaries();
			
			return beneficiaries.stream()
					.map(beneficiaryMapper::convertToDTO)
					.collect(Collectors.toList());
		}
    	else {
			throw new ResourceNotFoundException("Id Not Found");
		}
    }
 
    @Override
    public List<BeneficiaryDTO> getByPolicyId(int policyId) {
    	Optional<Policy> p = policyRepository.findById(policyId);
    	if (p.isPresent())
		{
			List<Beneficiary> beneficiaries=p.get().getBeneficiaries();
			
			return beneficiaries.stream()
					.map(beneficiaryMapper::convertToDTO)
					.collect(Collectors.toList());
		}
    	else {
			throw new ResourceNotFoundException("Id Not Found");
		}
    }
}
