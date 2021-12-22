package com.alkemy.disney.app.exceptions;

public class GenreServiceException extends RuntimeException {

	private static final long serialVersionUID = -1572518651923096530L;

	public GenreServiceException(String message) {
		 super(message);
	}
}
