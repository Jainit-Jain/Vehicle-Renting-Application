package com.example.vehiclerentingapplication.entity;

import com.example.vehiclerentingapplication.enums.Seating;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class VehicleListing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listingId;

	private String vehicleNo;

	private double pricePerDay;

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	private Seating seating;

	@ManyToOne(fetch = FetchType.LAZY)
	private Vehicle vehicle;

	@ManyToOne(fetch = FetchType.LAZY)
	private User rentingPartner;

	public User getRentingPartner() {
		return rentingPartner;
	}

	public void setRentingPartner(User rentingPartner) {
		this.rentingPartner = rentingPartner;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Seating getSeating() {
		return seating;
	}

	public void setSeating(Seating seating) {
		this.seating = seating;
	}

}
