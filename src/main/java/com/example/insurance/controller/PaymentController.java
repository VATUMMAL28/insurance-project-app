package com.example.insurance.controller;

import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.insurance.dto.PaymentDTO;
import com.example.insurance.service.PaymentService;
 
import lombok.AllArgsConstructor;
 
@RestController
@AllArgsConstructor
@RequestMapping("/api/insurance/payments")
public class PaymentController {
		PaymentService paymentService;
 
	    @PostMapping("/save")
	    public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO paymentDTO) {
	        PaymentDTO savedPayment = paymentService.savePayment(paymentDTO);
	        return new ResponseEntity<PaymentDTO>(savedPayment,HttpStatus.CREATED);
	    }
 
	    @GetMapping("/all")
	    public ResponseEntity<List<PaymentDTO>> viewAllPaymentDetails() {
	        List<PaymentDTO> payments = paymentService.viewAllPaymentDetails();
	        return new ResponseEntity<List<PaymentDTO>>(payments,HttpStatus.OK);
	    }
 
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<PaymentDTO>> getByPaymentUserId(@PathVariable int userId) {
	        List<PaymentDTO> payment = paymentService.getPaymentByUserId(userId);
	        return new ResponseEntity<List<PaymentDTO>>(payment,HttpStatus.OK);
	    }
}