package com.alkemy.disney.app.service;

import com.alkemy.disney.app.shared.dto.MovieDto;

public interface MovieService {
	public MovieDto getMovieById(String id) throws Exception;
	public MovieDto createMovie(MovieDto movie);
	public void deleteMovie(String movieId);

}
