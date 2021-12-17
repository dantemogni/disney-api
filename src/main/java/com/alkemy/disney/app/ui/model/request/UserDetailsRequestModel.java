package com.alkemy.disney.app.ui.model.request;

import lombok.Getter;
import lombok.Setter;

public class UserDetailsRequestModel {
	/**
	 * Spring se encarga de convertir JSON a esta clase
	 */
	@Getter
	@Setter
	private String username, email, password;
}
