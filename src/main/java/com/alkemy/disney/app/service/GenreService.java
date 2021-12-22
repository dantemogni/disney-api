package com.alkemy.disney.app.service;

import java.util.List;

import com.alkemy.disney.app.shared.dto.GenreDto;

public interface GenreService {
	public GenreDto getGenreById(String id);
	public void deleteGenre(String id);
	public GenreDto createGenre(GenreDto genre);
	public GenreDto updateGenre(String id, GenreDto genre);
	public List<GenreDto> getGenres(int page, int limit);
}
