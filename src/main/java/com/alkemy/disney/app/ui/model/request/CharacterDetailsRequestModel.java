package com.alkemy.disney.app.ui.model.request;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterDetailsRequestModel {
	private String name, image, story;
	private int age;
	private double weight;
	private List<MovieEntity> linkedMovies = new ArrayList<MovieEntity>();
}
