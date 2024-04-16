package com.example.insurance.service;

import java.util.List;


import com.example.insurance.dto.PaymentDTO;

public interface PaymentService 
{
	public PaymentDTO savePayment(PaymentDTO paymentdto);
	
    public List<PaymentDTO> viewAllPaymentDetails();
    
	public List<PaymentDTO> getPaymentByUserId(int userId);
}
