package com.alkemy.disney.app.ui.model.response;

import lombok.Getter;
import lombok.Setter;

public class UserRest {
	/*
	 * Devuelve data en formato JSON
	 */
	@Setter
	@Getter
	private String userId, username, email;
}
