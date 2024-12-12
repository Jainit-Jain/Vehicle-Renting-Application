package com.example.vehiclerentingapplication.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.service.ImageService;
import com.example.vehiclerentingapplication.util.SimpleResponseStructure;

@RestController
public class ImageController {
	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;

	}

	@PostMapping("/users/profile-picture")
	public ResponseEntity<SimpleResponseStructure> addProfilePicture(@RequestParam("userId") int userId,
			@RequestParam("file") MultipartFile file) throws IOException {
		imageService.addUserProfilePicture(userId, file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(SimpleResponseStructure.create(HttpStatus.OK.value(), "Profile picture added successfully"));
	}

	@GetMapping("/find/imageById")
	public ResponseEntity<byte[]> findImageById(@RequestParam int imageId) {
		Image image = imageService.findImageById(imageId);
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.contentType(MediaType.valueOf(image.getContentType()))
				.body(image.getImageBytes());
	}
}
