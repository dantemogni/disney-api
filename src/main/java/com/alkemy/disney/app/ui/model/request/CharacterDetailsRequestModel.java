package com.alkemy.disney.app.ui.model.request;

import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDetailsRequestModel {
	private String name, image, story;
	private int age;
	private double weight;
	private Set<MovieEntity> linkedMovies = new HashSet<MovieEntity>();
}
