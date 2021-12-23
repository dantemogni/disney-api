package com.alkemy.disney.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.app.service.UserService;
import com.alkemy.disney.app.shared.dto.UserDto;
import com.alkemy.disney.app.ui.model.request.UserDetailsRequestModel;
import com.alkemy.disney.app.ui.model.response.UserRest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@GetMapping(path="/{id}")
	public ResponseEntity<UserRest> getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@GetMapping
	public ResponseEntity<List<UserRest>> getUsers(@RequestParam(value="page", defaultValue="0") int page,
								   @RequestParam(value="limit", defaultValue="25") int limit) {
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		for(UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		return new ResponseEntity<List<UserRest>>(returnValue, HttpStatus.OK);
		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<UserRest> updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {		
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
