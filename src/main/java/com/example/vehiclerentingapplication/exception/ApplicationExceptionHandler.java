package com.example.vehiclerentingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.vehiclerentingapplication.util.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleUserNotFoundById(UserNotFoundByIdException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorStructure.create(HttpStatus.NOT_FOUND.value(),
				ex.getMessage(), "failed to find the user with given id"));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleFailedToUploadImage(FailedToUploadImageException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				ErrorStructure.create(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "failed to upload the image"));
	}
}
