package com.example.vehiclerentingapplication.service;

import org.springframework.stereotype.Service;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.mapper.UserMapper;
import com.example.vehiclerentingapplication.repository.UserRepository;
import com.example.vehiclerentingapplication.request.UserRequest;
import com.example.vehiclerentingapplication.response.UserResponse;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository,UserMapper userMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper=userMapper;
	}

	 public UserResponse saveUser(UserRequest userRequest) {
	        User user = userMapper.mapToUser(userRequest);
	        User savedUser = userRepository.save(user);
	        return userMapper.mapToResponse(savedUser);
	    }
}