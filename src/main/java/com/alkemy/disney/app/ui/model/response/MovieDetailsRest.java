package com.alkemy.disney.app.ui.model.response;

import java.time.LocalDate;
import java.util.Set;

import com.alkemy.disney.app.io.entity.CharacterEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRest {
	private String movieId, image, title;
	private LocalDate creationDate;
	private int rating;
	private Set<CharacterEntity> characters;
}
