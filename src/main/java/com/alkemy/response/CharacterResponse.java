package com.alkemy.response;

import com.alkemy.entity.Character;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponse {

	private Long id;
	private String image;
	private String name;
	private Integer age;
	private Double weight;
	private String story;

	public CharacterResponse(Character character) {
		this.id = character.getId();
		this.image = character.getImage();
		this.name = character.getName();
		this.age = character.getAge();
		this.weight = character.getWeight();
		this.story = character.getStory();
	}
	
}
