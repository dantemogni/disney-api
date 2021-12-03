package com.alkemy.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieRequest {
	@NotNull(message = "Movie/Series ID is requiered")
	private Long id;
	private String image;
	private String title;
	private Integer rating;
}
