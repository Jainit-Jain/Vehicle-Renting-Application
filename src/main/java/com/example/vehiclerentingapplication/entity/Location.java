package com.example.vehiclerentingapplication.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int locationId;
	private String addressLine;
	private String addressLineOptional;
	private String city;
	private String state;
	private String country;
	private int pinCode;
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "location")
	private List<VehicleListing> vehicleListings;

	public List<VehicleListing> getVehicleListings() {
		return vehicleListings;
	}

	public void setVehicleListings(List<VehicleListing> vehicleListings) {
		this.vehicleListings = vehicleListings;
	}

	@ManyToOne(fetch = FetchType.LAZY)

	private User user;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getAddressLineOptional() {
		return addressLineOptional;
	}

	public void setAddressLineOptional(String addressLineOptional) {
		this.addressLineOptional = addressLineOptional;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
