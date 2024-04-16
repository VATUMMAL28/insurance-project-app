package com.example.insurance.mapper;

import org.springframework.stereotype.Component;

import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.User;

@Component
public class UserMapper {
	public User userDTOtoUser(UserDTO dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setUserName(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setAddress(dto.getAddress());
		user.setCity(dto.getCity());
		user.setState(dto.getState());
		user.setZipCode(dto.getZipCode());
		user.setPassword(dto.getPassword());
		return user;
	}
	
	public UserDTO UserstoUsersDTO (User user) {
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setUsername(user.getUserName());
		dto.setPassword("********");
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setDateOfBirth(user.getDateOfBirth());
		dto.setAddress(user.getAddress());
		dto.setCity(user.getCity());
		dto.setState(user.getState());
		dto.setZipCode(user.getZipCode());
		return dto;
	}
	
}
