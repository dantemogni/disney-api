package com.alkemy.disney.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.alkemy.disney.app.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto user);
	public UserDto updateUser(String userId, UserDto user);
	public void deleteUser(String userId);
	public UserDto getUser(String username);
	public UserDto getUserByUserId(String id);
	public List<UserDto> getUsers(int page, int limit);
}
