package com.alkemy.disney.app.ui.model.response;

import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.app.io.entity.CharacterEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRest {
	private String movieId, image, title;
	private int rating;
	private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();
}
