package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class UserDto implements Serializable {
	/*
	 * Data Transfer Object. Se usa para trasferir un User con toda su informacion de aqui para alla
	 */
	private static final long serialVersionUID = 2809618991589091777L;
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String userId, username, email, password, encryptedPassword, emailVerificationToken;
	
	@Getter
	@Setter
	private Boolean emailVerificationStatus = false;
	
}
