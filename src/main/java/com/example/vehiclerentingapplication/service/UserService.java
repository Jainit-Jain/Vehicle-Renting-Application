package com.example.vehiclerentingapplication.service;

import org.springframework.stereotype.Service;

import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}
}
