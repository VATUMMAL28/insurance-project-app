package com.example.insurance.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BeneficiaryDTO {
	int beneficiaryId;
	int userId;
	int policyId;
	String name;
	String relationship;
}