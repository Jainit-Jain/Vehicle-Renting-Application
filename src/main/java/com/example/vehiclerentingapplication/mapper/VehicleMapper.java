package com.example.vehiclerentingapplication.mapper;

import org.springframework.stereotype.Component;

import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.request.VehicleRequest;
import com.example.vehiclerentingapplication.response.VehicleResponse;

@Component
public class VehicleMapper {

	public Vehicle mapToVehicle(VehicleRequest vehicleRequest, Vehicle vehicle) {
		if (vehicleRequest.getBrand() != null) {
			vehicle.setBrand(vehicleRequest.getBrand());
		}
		if (vehicleRequest.getModel() != null) {
			vehicle.setModel(vehicleRequest.getModel());
		}
		if (vehicleRequest.getVehicleType() != null) {
			vehicle.setVehicleType(vehicleRequest.getVehicleType());
		}
		if (vehicleRequest.getFuelType() != null) {
			vehicle.setFuleType(vehicleRequest.getFuelType());
		}
		return vehicle;
	}

	public VehicleResponse mapToResponse(Vehicle vehicle) {
		VehicleResponse response = new VehicleResponse();
		response.setVehicleId(vehicle.getVehicleId());
		response.setBrand(vehicle.getBrand());
		response.setModel(vehicle.getModel());
		response.setVehicleType(vehicle.getVehicleType());
		response.setFuelType(vehicle.getFuleType());
		return response;
	}
}
