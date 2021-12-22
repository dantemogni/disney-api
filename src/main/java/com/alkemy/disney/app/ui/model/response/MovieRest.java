package com.alkemy.disney.app.ui.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRest {
	private String movieId, image, title;
	private int rating;
}
