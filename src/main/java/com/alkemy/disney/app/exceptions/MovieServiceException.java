package com.alkemy.disney.app.exceptions;

public class MovieServiceException extends RuntimeException {

	private static final long serialVersionUID = -4289324597191619778L;
	
	public MovieServiceException(String message) {
		 super(message);
	}
}
