package com.example.vehiclerentingapplication.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.exception.FailedToUploadImageException;
import com.example.vehiclerentingapplication.exception.ImageNotFoundException;
import com.example.vehiclerentingapplication.exception.UserNotFoundByIdException;
import com.example.vehiclerentingapplication.repository.ImageRepository;
import com.example.vehiclerentingapplication.repository.UserRepository;

@Service
public class ImageService {

	private final ImageRepository imageRepository;

	private final UserRepository userRepository;

	public ImageService(ImageRepository imageRepository, UserRepository repository) {
		super();
		this.imageRepository = imageRepository;
		this.userRepository = repository;
	}

	public void addUserProfilePicture(int userId, MultipartFile multipartFile) {

		Optional<User> optional = userRepository.findById(userId);

		if (optional.isPresent()) {

			Image image = ImageService.getImage(multipartFile);
			image = imageRepository.save(image);

			User user = optional.get();
			user.setProfilePicture(image);
			userRepository.save(user);
		} else {
			throw new UserNotFoundByIdException("Failed to Find the user");
		}
	}

	public void uploadUserProfilePicture(int userId, MultipartFile file) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			if (user.getProfilePicture() != null) {
				Image image = user.getProfilePicture();
				this.uploadUserProfilePicture(userId, file);
				imageRepository.delete(image);
			}
			this.uploadUserProfile(file, user);
		} else {
			throw new UserNotFoundByIdException("User not found");
		}

	}

	private void uploadUserProfile(MultipartFile file, User user) {
		Image image = imageRepository.save(this.getImage(file));
		user.setProfilePicture(image);
		userRepository.save(user);

	}

	public static Image getImage(MultipartFile imagefile) {
		Image image = new Image();
		try {
			byte[] imageBytes = imagefile.getBytes();
			image.setContentType(imagefile.getContentType());
			image.setImageBytes(imageBytes);
		} catch (IOException e) {
			throw new FailedToUploadImageException("Failed to upload the Image");
		}

		return image;
	}

	public Image findImageById(int imageId) {
		Optional<Image> optional = imageRepository.findById(imageId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ImageNotFoundException("Failed to find the image with the given Id");
		}
	}
}
