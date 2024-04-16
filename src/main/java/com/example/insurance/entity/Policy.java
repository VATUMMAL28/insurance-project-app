package com.example.insurance.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,unique = true)
	private int policyId;
	private int policyNumber;
	
	@Min(value = 0, message = "Coverage amount must be positive")
	private int coverageAmount;
	
	@Min(value = 12, message = "Duration must be at least 12 month")
	private int duration;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@Min(value = 500, message = "Premium must be positive")
	private int premium;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "policies", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Payment> payments;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "policies", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Transaction> transactions;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "policies", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Beneficiary> beneficiaries;

	@JsonManagedReference
	@ManyToMany(mappedBy = "policies", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	List<User> user;
	
	public void addUsers (User u) {
		if (user == null) {
			user = new ArrayList<>();
		}
		user.add(u);
	}
	
	public void removeBeneficiary(Beneficiary b) {
		beneficiaries.remove(b);
	}
}
