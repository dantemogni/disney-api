package com.alkemy.disney.app.ui.model.response;

import java.util.List;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDetailsRest {
	private String characterId, image, name;
	private List<MovieEntity> linkedMovies;
}
