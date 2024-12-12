package com.example.vehiclerentingapplication.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.exception.FailedToUploadImageException;
import com.example.vehiclerentingapplication.exception.UserNotFoundByIdException;
import com.example.vehiclerentingapplication.repository.ImageRepository;
import com.example.vehiclerentingapplication.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final ImageRepository imageRepository;

	public UserService(UserRepository userRepository, ImageRepository imageRepository) {
		super();
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
}