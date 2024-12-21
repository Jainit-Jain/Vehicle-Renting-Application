package com.example.vehiclerentingapplication.service;

import org.springframework.stereotype.Service;
import com.example.vehiclerentingapplication.entity.Location;
import com.example.vehiclerentingapplication.entity.VehicleListing;
import com.example.vehiclerentingapplication.exception.NoVehicleListingException;
import com.example.vehiclerentingapplication.mapper.LocationMapper;
import com.example.vehiclerentingapplication.repository.LocationRepository;
import com.example.vehiclerentingapplication.repository.VehicleListingRepository;
import com.example.vehiclerentingapplication.request.LocationRequest;
import com.example.vehiclerentingapplication.response.LocationResponse;
import com.example.vehiclerentingapplication.security.OAuthUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

	private final VehicleListingRepository vehicleListingRepository;
	private final LocationMapper locationMapper;
	private final OAuthUtil authUtil;
	private LocationRepository locationRepository;

	public LocationService(VehicleListingRepository vehicleListingRepository, LocationMapper locationMapper,
			OAuthUtil authUtil, LocationRepository locationRepository) {
		super();
		this.vehicleListingRepository = vehicleListingRepository;
		this.locationMapper = locationMapper;
		this.authUtil = authUtil;
		this.locationRepository = locationRepository;
	}

	

	public LocationResponse addLocation(LocationRequest request) {

		Location location = locationMapper.mapToRequest(request);
		location.setUser(authUtil.getCurrentUser());
		location = locationRepository.save(location);

		return locationMapper.mapToResponse(location);
	}
	
	public List<LocationResponse> getLocationsByVehicleListingId(int listingId) {
	    List<Location> locations = vehicleListingRepository.findLocationsByVehicleListingId(listingId);
	    List<LocationResponse> locationResponses = new ArrayList();
	    for (Location location : locations) {
	        locationResponses.add(locationMapper.mapToResponse(location));
	    }
	    return locationResponses;
	}

}
