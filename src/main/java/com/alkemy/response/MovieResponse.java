package com.alkemy.response;

import com.alkemy.entity.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponse {
	private Long id;
	private String image;
	private String title;
	private Integer rating;
	
	public MovieResponse(Movie movie) {
		this.image = movie.getImage();
		this.title = movie.getTitle();
		this.rating = movie.getRating();
	}
}
