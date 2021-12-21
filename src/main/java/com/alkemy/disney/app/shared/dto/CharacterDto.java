package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class CharacterDto implements Serializable{

	private static final long serialVersionUID = 9087319657624196186L;
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String characterId, name, image, story;
	
	@Getter
	@Setter
	private int age;
	
	@Getter
	@Setter
	private double weight;
}
