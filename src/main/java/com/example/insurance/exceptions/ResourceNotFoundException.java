package com.example.insurance.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
} 

