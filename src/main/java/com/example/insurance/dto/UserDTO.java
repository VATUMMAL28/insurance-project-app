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
public class UserDTO {
	
	int userId;
	String username;
	String password;
	String email;
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
	String address;
	String city;
	String state;
	String zipCode;
	
}
