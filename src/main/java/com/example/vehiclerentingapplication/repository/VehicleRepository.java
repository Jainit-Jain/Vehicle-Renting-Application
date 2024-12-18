package com.example.vehiclerentingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vehiclerentingapplication.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
