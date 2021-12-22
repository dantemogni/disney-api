package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class GenreDto implements Serializable {

	private static final long serialVersionUID = 8058093790817969475L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String genreId, image, name;
	
	@Getter
	@Setter
	private Set<MovieEntity> movies = new HashSet<MovieEntity>();

}
