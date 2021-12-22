package com.alkemy.disney.app.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.app.exceptions.MovieServiceException;
import com.alkemy.disney.app.io.entity.MovieEntity;
import com.alkemy.disney.app.io.repository.MovieRepository;
import com.alkemy.disney.app.service.MovieService;
import com.alkemy.disney.app.shared.Utils;
import com.alkemy.disney.app.shared.dto.MovieDto;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	Utils utils;

	@Override
	public MovieDto getMovieById(String id) {
		MovieEntity movieEntity = movieRepository.findByMovieId(id);
		
		if(movieEntity == null) throw new MovieServiceException("Movie not found");
		
		MovieDto returnValue = new MovieDto();
		BeanUtils.copyProperties(movieEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public MovieDto createMovie(MovieDto movie) {
		MovieEntity movieEntity = new MovieEntity();
		BeanUtils.copyProperties(movie, movieEntity);
		
		movieEntity.setMovieId(utils.generateUserId(30));
		
		MovieEntity storedMovieDetails = movieRepository.save(movieEntity);
		
		MovieDto returnValue = new MovieDto();
		BeanUtils.copyProperties(storedMovieDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteMovie(String movieId) {
		MovieEntity movieEntity = movieRepository.findByMovieId(movieId);
		if(movieEntity == null) throw new MovieServiceException("Movie with ID:" + movieId + " not found");

		movieRepository.delete(movieEntity);		
	}
}
