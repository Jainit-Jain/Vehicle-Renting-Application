package com.example.vehiclerentingapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclerentingapplication.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
