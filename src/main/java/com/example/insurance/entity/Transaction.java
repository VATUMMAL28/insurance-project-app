package com.example.insurance.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,unique = true)
	private int transactionId;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "userId")
	User user;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "policyId")
	Policy policies;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "paymentId")
	Payment payments;
	

	private LocalDate transactionDate;
	
	@Size(min = 1, max = 255, message = "Status must be between 1 and 255 characters")
	private String status;
	
	public void removePolicy(Policy p) {
		policies=null;
	}
	
}
