package com.alkemy.disney.app.exceptions;

public class UserServiceException extends RuntimeException{
	private static final long serialVersionUID = -4103866910535275780L;

	public UserServiceException(String message) {
		 super(message);
	}
}
