package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.app.io.entity.MovieEntity;

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
	
	@Getter
	@Setter
	private Set<MovieEntity> linkedMovies = new HashSet<MovieEntity>();

}
