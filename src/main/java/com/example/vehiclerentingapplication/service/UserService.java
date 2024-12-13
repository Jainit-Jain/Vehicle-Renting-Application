package com.example.vehiclerentingapplication.service;

import org.springframework.stereotype.Service;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.exception.UserNotFoundByIdException;
import com.example.vehiclerentingapplication.mapper.UserMapper;
import com.example.vehiclerentingapplication.repository.UserRepository;
import com.example.vehiclerentingapplication.request.UserRequest;
import com.example.vehiclerentingapplication.response.UserResponse;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserResponse saveUser(UserRequest userRequest) {
		User user = userMapper.mapToUser(userRequest);
		User savedUser = userRepository.save(user);
		return userMapper.mapToResponse(savedUser);
	}

	public UserResponse getUserById(int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User Not Found By Id"));

		UserResponse userResponse = userMapper.mapToResponse(user);

		Integer profilePictureId = userRepository.findImageIdByUserId(userId);

		if (profilePictureId != null) {
			userResponse.setProfilePictureLink("/find/imageById?imageId=" + profilePictureId);
		} else {
			userResponse.setProfilePictureLink(null);
		}

		return userResponse;
	}
}
