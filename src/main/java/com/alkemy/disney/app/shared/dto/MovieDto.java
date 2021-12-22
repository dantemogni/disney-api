package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alkemy.disney.app.io.entity.CharacterEntity;
import com.alkemy.disney.app.io.entity.GenreEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MovieDto implements Serializable {

	private static final long serialVersionUID = -781218889110904788L;
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String movieId, image, title;
	
	@Getter
	@Setter
	private int rating;
	
	@Getter
	@Setter
	private LocalDate creationDate;
	
	@Getter
	@Setter
	private List<CharacterEntity> characters = new ArrayList<CharacterEntity>();
	
	@Getter
	@Setter
	private List<GenreEntity> linkedGenres = new ArrayList<GenreEntity>();


}
