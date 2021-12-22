package com.alkemy.disney.app.shared.dto;

import java.io.Serializable;

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
}
