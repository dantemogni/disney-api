package com.alkemy.disney.app.ui.controller.auth;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.app.exceptions.UserServiceException;
import com.alkemy.disney.app.service.UserService;
import com.alkemy.disney.app.shared.dto.UserDto;
import com.alkemy.disney.app.ui.model.request.UserDetailsRequestModel;
import com.alkemy.disney.app.ui.model.response.ErrorMessages;
import com.alkemy.disney.app.ui.model.response.UserRest;

@RestController
@RequestMapping("auth")
public class RegisterController {
	
	@Autowired
	UserService userService;

	@PostMapping("register")
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		
		UserRest returnValue = new UserRest();
		
		if(userDetails.getUsername().isEmpty()
				|| userDetails.getPassword().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		// Devuelve el usuario creado como json
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
}
