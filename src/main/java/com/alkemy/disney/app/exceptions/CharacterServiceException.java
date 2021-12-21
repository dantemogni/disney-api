package com.alkemy.disney.app.exceptions;

public class CharacterServiceException extends RuntimeException {
	private static final long serialVersionUID = 3499203625390870481L;

	public CharacterServiceException(String message) {
		super(message);
	}
}
