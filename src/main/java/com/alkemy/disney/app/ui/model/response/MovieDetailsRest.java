package com.alkemy.disney.app.ui.model.response;

import java.time.LocalDate;
import java.util.List;

import com.alkemy.disney.app.io.entity.CharacterEntity;
import com.alkemy.disney.app.io.entity.GenreEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRest {
	private String movieId, image, title;
	private LocalDate creationDate;
	private int rating;
	private List<CharacterEntity> characters;
	private List<GenreEntity> linkedGenres;


}
