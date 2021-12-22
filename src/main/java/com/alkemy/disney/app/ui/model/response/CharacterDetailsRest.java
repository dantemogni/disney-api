package com.alkemy.disney.app.ui.model.response;

import java.util.Set;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDetailsRest {
	private String characterId, image, name;
	private Set<MovieEntity> linkedMovies;
}
