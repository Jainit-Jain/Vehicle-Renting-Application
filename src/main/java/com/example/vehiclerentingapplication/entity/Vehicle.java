package com.example.vehiclerentingapplication.entity;

import java.util.List;

import com.example.vehiclerentingapplication.enums.FuelType;
import com.example.vehiclerentingapplication.enums.VehicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehicleId;

	private String brand;

	private String model;

	private VehicleType vehicleType;

	private FuelType fuelType;

	@OneToMany
	  private List<Image> images;
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}


	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

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

	public FuelType getFuleType() {
		return fuelType;
	}

	public void setFuleType(FuelType fuleType) {
		this.fuelType = fuleType;
	}

}
