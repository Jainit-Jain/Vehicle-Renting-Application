package com.example.vehiclerentingapplication.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.vehiclerentingapplication.entity.User;
import com.example.vehiclerentingapplication.repository.UserRepository;

@Component
public class OAuthUtil {

	private final UserRepository repository;

	public OAuthUtil(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getCurrentUsername() {
		return this.getAuthentication().getName();
	}

	public User getCurrentUser() {
		return repository.findByEmail(getCurrentUsername())
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}

}
