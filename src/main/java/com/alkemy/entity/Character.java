package com.alkemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alkemy.request.CreateCharacterRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "weight")
	private Double weight;
	
	@Column(name = "story")
	private String story;
	
	public Character(CreateCharacterRequest createCharacterRequest) {
		this.image = createCharacterRequest.getImage();
		this.name = createCharacterRequest.getName();
		this.age = createCharacterRequest.getAge();
		this.weight = createCharacterRequest.getWeight();
		this.story = createCharacterRequest.getStory();
	}
	
}
