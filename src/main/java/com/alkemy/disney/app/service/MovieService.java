package com.alkemy.disney.app.service;

import java.util.List;

import com.alkemy.disney.app.shared.dto.MovieDto;

public interface MovieService {
	public MovieDto getMovieById(String id) throws Exception;
	public MovieDto createMovie(MovieDto movie);
	public void deleteMovie(String movieId);
	public MovieDto updateMovie(String id, MovieDto movie);
	public List<MovieDto> getMovies(int page, int limit);

}
