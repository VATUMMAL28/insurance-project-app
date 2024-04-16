package com.example.insurance.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.insurance.dto.PaymentDTO;
import com.example.insurance.entity.Payment;
import com.example.insurance.entity.User;
import com.example.insurance.repository.PaymentRepository;
import com.example.insurance.repository.UserRepository;
import com.example.insurance.exceptions.ResourceNotFoundException;
import com.example.insurance.mapper.PaymentMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor			//For Dependency Injection
@Service
public class PaymentServiceImpl implements PaymentService{
	
	PaymentRepository paymentRepo;			//Reference of Payment Repository 
	UserRepository userRepo;				//Reference of User Repository
	PaymentMapper paymentMapper;
	
	//To save the payment details
	@Override
	public PaymentDTO savePayment(PaymentDTO paymentdto) 
	{
		Payment payment=paymentMapper.convertFromDTO(paymentdto);
		return paymentMapper.convertToDTO(paymentRepo.save(payment));
	}
	
	
	//To get all the payment details
	@Override
	public List<PaymentDTO> viewAllPaymentDetails()
	{
		
		List<Payment> payments=paymentRepo.findAll();
		
		
		return payments.stream()
                .map(paymentMapper::convertToDTO)
                .collect(Collectors.toList());
	}
	
	
	//To get the list of payments made by a particular user 
	@Override
	public List<PaymentDTO> getPaymentByUserId(int userId)
	{
		Optional<User> u = userRepo.findById(userId);
		if (u.isPresent())
		{
			List<Payment> payments=u.get().getPayments();
			
			return payments.stream()
					.map(paymentMapper::convertToDTO)
					.collect(Collectors.toList());
		}
		
		else {
			throw new ResourceNotFoundException("Id Not Found");
		}
		
	}
}
