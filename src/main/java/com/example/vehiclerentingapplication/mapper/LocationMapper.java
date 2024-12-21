package com.example.vehiclerentingapplication.mapper;

import com.example.vehiclerentingapplication.request.LocationRequest;
import com.example.vehiclerentingapplication.response.LocationResponse;
import org.springframework.stereotype.Component;
import com.example.vehiclerentingapplication.entity.Location;

@Component
public class LocationMapper {

	public Location mapToRequest(LocationRequest request) {
		Location location = new Location();
		location.setAddressLine(request.getAddressLine());
		location.setAddressLineOptional(request.getAddressLineOptional());
		location.setCity(request.getCity());
		location.setState(request.getState());
		location.setCountry(request.getCountry());
		location.setPinCode(request.getPinCode());
		location.setPhoneNumber(request.getPhoneNumber());
		return location;
	}

	public LocationResponse mapToResponse(Location location) {
		LocationResponse response = new LocationResponse();
		response.setLocationId(location.getLocationId());
		response.setAddressLine(location.getAddressLine());
		response.setAddressLineOptional(location.getAddressLineOptional());
		response.setCity(location.getCity());
		response.setState(location.getState());
		response.setCountry(location.getCountry());
		response.setPinCode(location.getPinCode());
		response.setPhoneNumber(location.getPhoneNumber());
		return response;
	}
}
