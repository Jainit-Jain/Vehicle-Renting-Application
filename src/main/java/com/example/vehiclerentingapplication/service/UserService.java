package com.example.vehiclerentingapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.vehiclerentingapplication.mapper.UserMapper;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.enums.UserRole;
import com.example.vehiclerentingapplication.exception.UserNotFoundByIdException;
import com.example.vehiclerentingapplication.repository.ImageRepository;
import com.example.vehiclerentingapplication.repository.UserRepository;
import com.example.vehiclerentingapplication.request.UserRequest;
import com.example.vehiclerentingapplication.response.UserResponse;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper mapper;

	private final ImageRepository imageRepository;

	public UserService(UserRepository userRepository, UserMapper mapper, ImageRepository imageRepository) {
		super();
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.imageRepository = imageRepository;
	}

	public UserResponse register(UserRequest userRequest, UserRole userRole) {
		User user = mapper.mapToUser(userRequest, new User());
		user.setRole(userRole);
		user = userRepository.save(user);
		return mapper.mapToResponse(user);
	}

	public UserResponse findUserById(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User Not Found By ID"));
		UserResponse userResponse = mapper.mapToResponse(user);

		Integer profilePictureId = userRepository.findImageIdByUserId(userId);

		if (profilePictureId != null) {
			userResponse.setProfilePictureLink("/find/imageById?imageId=" + profilePictureId);
		} else {
			userResponse.setProfilePictureLink(null);
		}
		return userResponse;
	}

	public UserResponse updateUserById(int userId, UserRequest userRequest) {

		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User Not Found By ID: " + userId));

		mapper.mapToUser(userRequest, existingUser);

		userRepository.save(existingUser);

		UserResponse response = mapper.mapToResponse(existingUser);

		if (existingUser.getProfilePicture() != null) {
			response.setProfilePictureLink("/find/imageById?imageId=" + existingUser.getProfilePicture().getImageId());
		} else {
			response.setProfilePictureLink(null);
		}

		return response;
	}

}