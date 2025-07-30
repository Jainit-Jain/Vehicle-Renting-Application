package com.example.vehiclerentingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.vehiclerentingapplication.exception.VehicleNotFoundException;
import com.example.vehiclerentingapplication.request.VehicleRequest;
import com.example.vehiclerentingapplication.response.VehicleResponse;
import com.example.vehiclerentingapplication.entity.Image;
import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.mapper.VehicleMapper;
import com.example.vehiclerentingapplication.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;
	private final VehicleMapper vehicleMapper;
	private final ImageService imageService;

	public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, ImageService imageService) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.vehicleMapper = vehicleMapper;
		this.imageService = imageService;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public VehicleResponse register(VehicleRequest request) {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(request.getBrand());
		vehicle.setModel(request.getModel());
		vehicle.setVehicleType(request.getVehicleType());
		vehicle.setFuleType(request.getFuelType());
		Vehicle savedVehicle = vehicleRepository.save(vehicle);
		return vehicleMapper.mapToResponse(savedVehicle);
	}

	public List<VehicleResponse> findAllVehicles() {

		List<Vehicle> vehicles = vehicleRepository.findAll();
		List<VehicleResponse> vehicleResponses = new ArrayList();

		if (vehicles.isEmpty()) {
			throw new VehicleNotFoundException("No Vehicles present");
		}
		for (Vehicle vehicle : vehicles) {
			VehicleResponse response = vehicleMapper.mapToResponse(vehicle);
			vehicleResponses.add(response);
		}

		return vehicleResponses;
	}


	@PreAuthorize("hasAuthority('ADMIN')")
	public VehicleResponse updateVehicleById(int vehicleId, VehicleRequest request) {

		Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
		existingVehicle.setBrand(request.getBrand());
		existingVehicle.setModel(request.getModel());
		existingVehicle.setFuleType(request.getFuelType());

		Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

		return vehicleMapper.mapToResponse(updatedVehicle);
	}
}
