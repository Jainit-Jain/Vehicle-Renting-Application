package com.example.vehiclerentingapplication.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.exception.FailedToUploadImageException;
import com.example.vehiclerentingapplication.exception.ImageNotFoundException;
import com.example.vehiclerentingapplication.repository.ImageRepository;
import com.example.vehiclerentingapplication.repository.UserRepository;
import com.example.vehiclerentingapplication.repository.VehicleRepository;
import com.example.vehiclerentingapplication.security.OAuthUtil;

@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final UserRepository userRepository;
	private final VehicleRepository vehicleRepository;
	private final OAuthUtil authUtil;

	public ImageService(ImageRepository imageRepository, UserRepository userRepository,
			VehicleRepository vehicleRepository, OAuthUtil authUtil) {
		super();
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
		this.vehicleRepository = vehicleRepository;
		this.authUtil = authUtil;
	}

	public void uploadUserProfilePicture(MultipartFile file) {
		User user = authUtil.getCurrentUser();

		Image oldImage = user.getProfilePicture();
		if (oldImage != null) {
			imageRepository.delete(oldImage);
		}

		Image newImage = saveImage(file);
		user.setProfilePicture(newImage);
		userRepository.save(user);
	}

	public List<Image> saveImages(List<MultipartFile> files) {
		List<Image> images = new ArrayList<>();
		for (MultipartFile file : files) {
			try {
				Image image = new Image();
				image.setContentType(file.getContentType());
				image.setImageBytes(file.getBytes());
				images.add(imageRepository.save(image));
			} catch (IOException e) {
				throw new RuntimeException("Failed to save image", e);
			}
		}
		return images;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void addImagesToVehicle(int vehicleId, List<MultipartFile> files) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new IllegalArgumentException("Vehicle not found with ID: " + vehicleId));
		List<Image> images = saveImages(files);
		vehicle.getImages().addAll(images);

		vehicleRepository.save(vehicle);
	}

	public Image findImageByCurrentUser() {
		User user = authUtil.getCurrentUser();
		Image image = user.getProfilePicture();
		if (image == null) {
			throw new ImageNotFoundException("No profile picture found for the current user");
		}
		return image;
	}

	private Image saveImage(MultipartFile file) {
		try {
			Image image = new Image();
			image.setContentType(file.getContentType());
			image.setImageBytes(file.getBytes());
			return imageRepository.save(image);
		} catch (IOException e) {
			throw new FailedToUploadImageException("Failed to upload the image");
		}
	}
}
