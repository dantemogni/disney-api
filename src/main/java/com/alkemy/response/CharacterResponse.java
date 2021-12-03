package com.alkemy.response;

import com.alkemy.entity.Character;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponse {

	private String image;
	private String name;

	public CharacterResponse(Character character) {
		this.image = character.getImage();
		this.name = character.getName();
	}
	
}
