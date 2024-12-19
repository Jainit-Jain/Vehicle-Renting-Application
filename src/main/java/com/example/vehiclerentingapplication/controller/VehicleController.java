package com.example.vehiclerentingapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.request.VehicleRequest;
import com.example.vehiclerentingapplication.response.VehicleResponse;
import com.example.vehiclerentingapplication.service.ImageService;
import com.example.vehiclerentingapplication.service.VehicleService;
import com.example.vehiclerentingapplication.util.ResponseStructure;
import com.example.vehiclerentingapplication.util.SimpleResponseStructure;

import java.util.List;

@RestController
public class VehicleController {

	private final VehicleService vehicleService;

	private final ImageService imageService;

	public VehicleController(VehicleService vehicleService, ImageService imageService) {
		super();
		this.vehicleService = vehicleService;
		this.imageService = imageService;
	}

	@PostMapping("/register-vehicle")
	public ResponseEntity<ResponseStructure<VehicleResponse>> registerVehicle(@RequestBody VehicleRequest request) {
		VehicleResponse response = vehicleService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseStructure.create(HttpStatus.CREATED.value(), "Vehicle Registered Successfully", response));
	}

	@GetMapping("/find-all-vehicles")
	public ResponseEntity<ResponseStructure<List<VehicleResponse>>> findAllVehicles() {
		List<VehicleResponse> vehicleResponses = vehicleService.findAllVehicles();
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "All Vehicles Found", vehicleResponses));
	}

	@PutMapping("/update/vehicle-info")
	public ResponseEntity<ResponseStructure<VehicleResponse>> updateVehicle(@RequestParam("vehicleId") int vehicleId,
			@RequestBody VehicleRequest request) {
		VehicleResponse response = vehicleService.updateVehicleById(vehicleId, request);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.create(HttpStatus.OK.value(), "Vehicle Updated Successfully", response));
	}

	
}
