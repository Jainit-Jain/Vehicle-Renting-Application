package com.example.vehiclerentingapplication.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class UserNotFoundByIdException extends BaseException {

	public UserNotFoundByIdException(String message) {
		super(message);
		
	}
	


}
