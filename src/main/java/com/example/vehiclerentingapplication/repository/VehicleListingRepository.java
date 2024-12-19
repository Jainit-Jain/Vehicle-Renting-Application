package com.example.vehiclerentingapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.entity.VehicleListing;

public interface VehicleListingRepository extends JpaRepository<VehicleListing, Integer> {
	 List<VehicleListing> findByVehicle(Vehicle vehicle);
}
