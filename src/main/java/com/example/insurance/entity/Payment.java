package com.example.insurance.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int paymentId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "userId")
	User user;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "policyId")
	Policy policies;

	@JsonManagedReference
	@OneToMany(mappedBy = "payments", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Transaction> transactions;

	@Min(value = 0, message = "Payment amount must be positive")
	double paymentAmount;
	
	private LocalDate paymentDate;
	
	public void removePolicy(Policy p) {
		policies=null;
	}
}
