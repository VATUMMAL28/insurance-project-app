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
public class PolicyDTO {
	
	int policyId;
	int policyNumber;
	int coverageAmount;
	int duration;
	LocalDate startDate;
	LocalDate endDate;
	int premium;
}
