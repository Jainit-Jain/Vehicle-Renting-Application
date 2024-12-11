package com.example.vehiclerentingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vehiclerentingapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.username=:username")
	Optional<User>findByUsername(String userName);
}
