package com.example.insurance.exceptions;

public class PaymentsNotFoundException extends RuntimeException {
	public PaymentsNotFoundException(String message) {
		super(message);
	}
}
