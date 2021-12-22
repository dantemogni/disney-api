package com.alkemy.disney.app.ui.model.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRest {
	private String movieId, image, title;
	private LocalDate creationDate;
}
