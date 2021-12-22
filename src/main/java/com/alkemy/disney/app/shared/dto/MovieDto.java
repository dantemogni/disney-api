package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.app.io.entity.CharacterEntity;

import lombok.Getter;
import lombok.Setter;

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
	private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();

}
