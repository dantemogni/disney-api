package com.alkemy.disney.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.alkemy.disney.app.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto user);
	public UserDto getUser(String username);
	public UserDto getUserByUserId(String id);
}
