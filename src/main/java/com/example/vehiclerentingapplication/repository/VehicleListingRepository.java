package com.example.vehiclerentingapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.vehiclerentingapplication.entity.Location;
import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.entity.VehicleListing;

public interface VehicleListingRepository extends JpaRepository<VehicleListing, Integer> {
	List<VehicleListing> findByVehicle(Vehicle vehicle);

	@Query("SELECT l FROM Location l JOIN l.vehicleListings vl WHERE vl.listingId = :listingId")
	List<Location> findLocationsByVehicleListingId(int listingId);

}