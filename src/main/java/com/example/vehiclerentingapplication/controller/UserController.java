package com.example.vehiclerentingapplication.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.enums.UserRole;
import com.example.vehiclerentingapplication.request.UserRequest;
import com.example.vehiclerentingapplication.response.UserResponse;
import com.example.vehiclerentingapplication.service.UserService;
import com.example.vehiclerentingapplication.util.ResponseStructure;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/customer/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerCustomer(@RequestBody UserRequest request) {
		UserResponse response = userService.register(request, UserRole.CUSTOMER);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Customer Created", response));
	}

	@PostMapping("/renting_partner/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerRentingPartner(@RequestBody UserRequest request) {
		UserResponse response = userService.register(request, UserRole.RENTING_PARTNER);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Renting Partner Created", response));
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@RequestParam("userId") int userId) {
		UserResponse response = userService.findUserById(userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "User Found Successfully", response));
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestParam("userId") int userId,
	        @RequestBody UserRequest request) {
	    UserResponse response = userService.updateUserById(userId, request);
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(ResponseStructure.create(HttpStatus.OK.value(), "User Updated Successfully", response));
	}

}
