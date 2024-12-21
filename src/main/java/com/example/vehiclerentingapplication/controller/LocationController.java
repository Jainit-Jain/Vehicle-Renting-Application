package com.example.vehiclerentingapplication.controller;

import com.example.vehiclerentingapplication.request.LocationRequest;
import com.example.vehiclerentingapplication.response.LocationResponse;
import com.example.vehiclerentingapplication.service.LocationService;
import com.example.vehiclerentingapplication.util.ResponseStructure;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

	private final LocationService locationService;

	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}

	@PostMapping("/create/location")
	public ResponseEntity<ResponseStructure<LocationResponse>> addLocation(@RequestBody LocationRequest request) {
		LocationResponse response = locationService.addLocation(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "Location Created Successfully", response));
	}
	
	 @GetMapping("/locations/by-listing")
	    public ResponseEntity<ResponseStructure<List<LocationResponse>>> getLocationsByVehicleListingId(
	            @RequestParam("listingId") int listingId) {
	        List<LocationResponse> responses = locationService.getLocationsByVehicleListingId(listingId);
	        return ResponseEntity.ok(
	                ResponseStructure.create(HttpStatus.OK.value(), "Locations Retrieved Successfully", responses));
	    }

}
