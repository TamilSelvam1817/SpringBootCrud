package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	@GetMapping
//	public String getUsers() {
//		return "Hello Tamil";
//	}
	
	/*
	@GetMapping
	public List<User> getUsers() {
		return Arrays.asList(
				new User(1L, "Tamil", "tamil@gmail.com"),
				new User(2L, "Selvam", "selvam@gmail.com"),
				new User(3L, "Selvam", "selvam@gmail.com")
				);
	}
	*/
	
	@GetMapping
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);  
	}
	
	@GetMapping("/{id}")
	public UserEntity getuserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User Not found with this id : "+id));
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
		UserEntity userData = userRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("User Not found with this id : "+id));
		userData.setName(user.getName());
		userData.setEmail(user.getEmail());
		return userRepository.save(userData);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		UserEntity userData = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not found with this id : "+id));
		userRepository.delete(userData);
		return ResponseEntity.ok().build();
	}
}
