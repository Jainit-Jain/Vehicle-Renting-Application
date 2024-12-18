package com.example.vehiclerentingapplication.request;

import com.example.vehiclerentingapplication.enums.FuelType;
import com.example.vehiclerentingapplication.enums.VehicleType;

public class VehicleRequest {

	private String brand;
	private String model;
	private VehicleType vehicleType;
	private FuelType fuleType;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public FuelType getFuelType() {
		return fuleType;
	}

	public void setFuelType(FuelType fuleType) {
		this.fuleType = fuleType;
	}
}
