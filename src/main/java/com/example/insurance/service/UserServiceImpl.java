package com.example.insurance.service;

import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.User;
import com.example.insurance.exceptions.ResourceNotFoundException;
import com.example.insurance.mapper.UserMapper;
import com.example.insurance.repository.UserRepository;
import lombok.AllArgsConstructor;
 
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
 
	UserRepository userRepo;
	UserMapper userMapper;
 
	@Override
	public UserDTO addUser(UserDTO userDTO) {
		User user=userMapper.userDTOtoUser(userDTO);
		return userMapper.UserstoUsersDTO(userRepo.save(user));
	}
 
	@Override
    public UserDTO getUserById(int userId) {
        // Find the user by user ID
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
 
        // Map User entity to UserDTO and return
        return userMapper.UserstoUsersDTO(user);
    }
	

	@Override
	public UserDTO updateUser(int id, UserDTO userDTO) 
	{
		//Get the Existing User details to be Updated 
		User existingUser = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userDTO.getUserId()));
		
	
		existingUser.setUserName(userDTO.getUsername());
		existingUser.setPassword(userDTO.getPassword());
		existingUser.setEmail(userDTO.getEmail());
		existingUser.setFirstName(userDTO.getFirstName());
		existingUser.setLastName(userDTO.getLastName());
		existingUser.setDateOfBirth(userDTO.getDateOfBirth());
		existingUser.setAddress(userDTO.getAddress());
		existingUser.setCity(userDTO.getCity());
		existingUser.setState(userDTO.getState());
		existingUser.setZipCode(userDTO.getZipCode());
		
		existingUser=userRepo.save(existingUser);
		
		return userMapper.UserstoUsersDTO(existingUser);

		// Map updated User entity back to UserDTO
		//return userMapper.UserstoUsersDTO(existingUser);

	}
	
	@Override
    public UserDTO getUserByEmail(String email) {
        // Find the user by email
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
 
        // Map User entity to UserDTO and return
        return userMapper.UserstoUsersDTO(user);
    }
 
	@Override
    public List<UserDTO> getAllUsers() {
        // Retrieve all users from the repository
        List<User> users = userRepo.findAll();
 
        // Map each User entity to UserDTO using UserMapper
        return users.stream()
                .map(userMapper::UserstoUsersDTO)
                .collect(Collectors.toList());
    }
	
	@Override
    public UserDTO signIn(String userName, String password) {
        // Find the user by username
        User user = userRepo.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + userName));
 
        // Check if the provided password matches the user's password
        if (!user.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Incorrect password for user: " + userName);
        }
 
        // Map User entity to UserDTO and return
        return userMapper.UserstoUsersDTO(user);
    }
	
}


