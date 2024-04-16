package com.example.insurance.controller;

import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.insurance.dto.UserDTO;
import com.example.insurance.service.UserService;
 
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
 
@RestController
@AllArgsConstructor
@RequestMapping("/api/insurance/users")
public class UserController {
	UserService service;

 
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
    	UserDTO addedUser =service.addUser(userDTO);
        return new ResponseEntity<UserDTO> (addedUser,HttpStatus.CREATED);
    }
 
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id,@RequestBody UserDTO userDTO) {
    	UserDTO updatedUser =service.updateUser(id,userDTO);
        return new ResponseEntity<UserDTO>(updatedUser,HttpStatus.OK);
    }
 
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
    	UserDTO user = service.getUserById(userId);
        return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
    }
  
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = service.getUserByEmail(email);
        return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = service.getAllUsers();
        return new ResponseEntity<List<UserDTO>> (users,HttpStatus.OK);
    }
 
    @PostMapping("/signin")
    public ResponseEntity<UserDTO> signIn(@RequestParam String userName, @RequestParam String password) {
        UserDTO user = service.signIn(userName, password);
        return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
    }
     
}