package com.example.vehiclerentingapplication.service;

import org.springframework.stereotype.Service;

import com.example.vehiclerentingapplication.entity.Location;
import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.entity.Vehicle;
import com.example.vehiclerentingapplication.entity.VehicleListing;
import com.example.vehiclerentingapplication.exception.LocationNotFoundException;
import com.example.vehiclerentingapplication.exception.NoVehicleListingException;
import com.example.vehiclerentingapplication.exception.VehicleNotFoundException;
import com.example.vehiclerentingapplication.mapper.VehicleListingMapper;
import com.example.vehiclerentingapplication.repository.LocationRepository;
import com.example.vehiclerentingapplication.repository.VehicleListingRepository;
import com.example.vehiclerentingapplication.repository.VehicleRepository;
import com.example.vehiclerentingapplication.request.VehicleListingRequest;
import com.example.vehiclerentingapplication.response.VehicleListingResponse;
import com.example.vehiclerentingapplication.security.OAuthUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleListingService {

	private final VehicleListingRepository vehicleListingRepository;
	private final VehicleRepository vehicleRepository;
	private final VehicleListingMapper vehicleListingMapper;
	private final LocationRepository locationRepository;
	private final OAuthUtil authUtil;

	public VehicleListingService(VehicleListingRepository vehicleListingRepository, VehicleRepository vehicleRepository,
			VehicleListingMapper vehicleListingMapper, LocationRepository locationRepository, OAuthUtil authUtil) {
		super();
		this.vehicleListingRepository = vehicleListingRepository;
		this.vehicleRepository = vehicleRepository;
		this.vehicleListingMapper = vehicleListingMapper;
		this.locationRepository = locationRepository;
		this.authUtil = authUtil;
	}

	
	public VehicleListingResponse createVehicleListing(VehicleListingRequest request, int vehicleId) {
		User user = authUtil.getCurrentUser();
		if (vehicleId <= 0) {
			throw new RuntimeException("Invalid vehicle ID.");
		}

		if (request.getVehicleNo() == null || request.getVehicleNo().isEmpty()) {
			throw new RuntimeException("Vehicle number must not be null or empty.");
		}

		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId));
		VehicleListing vehicleListing = new VehicleListing();
		vehicleListing.setVehicle(vehicle);
		vehicleListing.setVehicleNo(request.getVehicleNo());
		vehicleListing.setPricePerDay(request.getPricePerDay());
		vehicleListing.setSeating(request.getSeating());
		vehicleListing.setRentingPartner(user);
		VehicleListing savedVehicleListing = vehicleListingRepository.save(vehicleListing);
		return vehicleListingMapper.mapToResponse(savedVehicleListing);
	}

	public VehicleListingResponse addLocationsToVehicleListing(int listingId, List<Integer> locationIds) {
		VehicleListing vehicleListing = vehicleListingRepository.findById(listingId)
				.orElseThrow(() -> new NoVehicleListingException("Vehicle Listing not found with ID: " + listingId));

		List<Location> locations = locationRepository.findAllById(locationIds);

		if (locations.size() != locationIds.size()) {
			throw new LocationNotFoundException("Some of the provided location IDs are invalid.");
		}

		vehicleListing.getLocation().addAll(locations);

		VehicleListing updatedVehicleListing = vehicleListingRepository.save(vehicleListing);

		VehicleListingResponse response = vehicleListingMapper.mapToResponse(updatedVehicleListing);

		return response;
	}

	public List<VehicleListingResponse> getVehicleListingsByVehicleId(int vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + vehicleId));
		List<VehicleListing> listings = vehicleListingRepository.findByVehicle(vehicle);

		if (listings.isEmpty()) {
			throw new NoVehicleListingException("No listings found for Vehicle ID: " + vehicleId);
		}

		List<VehicleListingResponse> responses = new ArrayList<>();
		for (VehicleListing listing : listings) {
			responses.add(vehicleListingMapper.mapToResponse(listing));
		}

		return responses;
	}

}
