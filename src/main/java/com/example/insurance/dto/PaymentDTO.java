package com.example.insurance.dto;

import java.time.LocalDate;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {
	
	int paymentId;
	int userId;
	int policyId;
	double paymentAmount;
	LocalDate paymentDate;
}
