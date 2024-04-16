package com.example.insurance.mapper;


import java.util.Optional;

import org.springframework.stereotype.Component;
 
import com.example.insurance.dto.PaymentDTO;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.Policy;
import com.example.insurance.entity.User;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PaymentMapper {
	
	UserRepository userrepo;
	PolicyRepository policyrepo;
	
	public Payment convertFromDTO(PaymentDTO dto) 
	{
        Payment payment = new Payment();
        payment.setPaymentId(dto.getPaymentId());
        
        Optional<User> user0 = userrepo.findById(dto.getUserId());
        payment.setUser(user0.get());
        
        Optional<Policy> policy0=policyrepo.findById(dto.getPolicyId());
        payment.setPolicies(policy0.get());
        
        payment.setPaymentAmount(dto.getPaymentAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        return payment;
    }
 
    public PaymentDTO convertToDTO(Payment payment) 
    {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setUserId(payment.getUser().getUserId());
        dto.setPolicyId(payment.getPolicies()==null?-1:payment.getPolicies().getPolicyId());
        dto.setPaymentAmount(payment.getPaymentAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }
}