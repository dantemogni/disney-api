package com.alkemy.disney.app.ui.model.response;

import java.util.Set;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDetailsRest {
	private String genreId, name, image;
	private Set<MovieEntity> movies;

}
