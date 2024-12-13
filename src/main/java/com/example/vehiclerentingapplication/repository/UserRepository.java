package com.example.vehiclerentingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vehiclerentingapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u.profilePicture.id FROM User u WHERE u.userId = :userId")
	Integer findImageIdByUserId(int userId);
}
