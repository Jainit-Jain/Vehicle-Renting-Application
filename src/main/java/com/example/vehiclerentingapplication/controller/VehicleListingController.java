package com.example.vehiclerentingapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.vehiclerentingapplication.request.VehicleListingRequest;
import com.example.vehiclerentingapplication.response.VehicleListingResponse;
import com.example.vehiclerentingapplication.service.VehicleListingService;
import com.example.vehiclerentingapplication.util.ResponseStructure;

import java.util.List;

@RestController
public class VehicleListingController {

    private final VehicleListingService vehicleListingService;

    public VehicleListingController(VehicleListingService vehicleListingService) {
        this.vehicleListingService = vehicleListingService;
    }

	@PreAuthorize("hasAuthority('RENTING_PARTNER')")
    @PostMapping("/create/vehicle-listing")
    public ResponseEntity<ResponseStructure<VehicleListingResponse>> createVehicleListing(@RequestParam("vehicleId") int vehicleId,
            @RequestBody VehicleListingRequest request) {
        VehicleListingResponse response = vehicleListingService.createVehicleListing(request,vehicleId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED.value(), "Vehicle Listing Created Successfully", response));
    }
	
	
	@GetMapping("/vehicle-listings")
	public ResponseEntity<ResponseStructure<List<VehicleListingResponse>>> getVehicleListingsByVehicleId(
	        @RequestParam("vehicleId") int vehicleId) {
	    List<VehicleListingResponse> responses = vehicleListingService.getVehicleListingsByVehicleId(vehicleId);
	    return ResponseEntity.ok(
	            ResponseStructure.create(HttpStatus.OK.value(), "Vehicle Listings Retrieved Successfully", responses));
	}


}
