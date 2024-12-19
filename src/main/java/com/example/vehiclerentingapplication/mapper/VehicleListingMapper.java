package com.example.vehiclerentingapplication.mapper;

import org.springframework.stereotype.Component;

import com.example.vehiclerentingapplication.entity.VehicleListing;
import com.example.vehiclerentingapplication.response.VehicleListingResponse;

@Component
public class VehicleListingMapper {

	public VehicleListingResponse mapToResponse(VehicleListing listing) {
		VehicleListingResponse response = new VehicleListingResponse();
		response.setListingId(listing.getListingId());
		response.setVehicleNo(listing.getVehicleNo());
		response.setPricePerDay(listing.getPricePerDay());
		response.setSeating(listing.getSeating());

		return response;
	}
}
