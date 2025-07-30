package com.example.vehiclerentingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vehiclerentingapplication.entity.Image;


public interface ImageRepository extends JpaRepository<Image, Integer> {

}
