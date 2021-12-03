package com.alkemy.exception;

public class CharacterNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2796785010967999448L;

	public CharacterNotFoundException(Long id){
		super("Could not find character " + id);
	}
}
