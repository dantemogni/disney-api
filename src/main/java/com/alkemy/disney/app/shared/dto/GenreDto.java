package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class GenreDto implements Serializable {

	private static final long serialVersionUID = 8058093790817969475L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String genreId, image, name;
}
