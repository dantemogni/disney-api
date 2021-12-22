package com.alkemy.disney.app.ui.model.request;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.app.io.entity.CharacterEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRequestModel {
	private String image, title;
	private LocalDate creationDate;
	private int rating;
	private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();

}
