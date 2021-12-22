package com.alkemy.disney.app.ui.model.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alkemy.disney.app.io.entity.GenreEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailsRequestModel {
	private String image, title;
	private LocalDate creationDate;
	private int rating;
	private List<GenreEntity> linkedGenres = new ArrayList<GenreEntity>();
	//private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();

}
