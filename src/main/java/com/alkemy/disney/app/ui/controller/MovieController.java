package com.alkemy.disney.app.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.app.exceptions.MovieServiceException;
import com.alkemy.disney.app.service.MovieService;
import com.alkemy.disney.app.shared.dto.MovieDto;
import com.alkemy.disney.app.ui.model.request.MovieDetailsRequestModel;
import com.alkemy.disney.app.ui.model.response.ErrorMessages;
import com.alkemy.disney.app.ui.model.response.MovieRest;

@RestController
@RequestMapping("movies")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping(path="/{id}")
	public MovieRest getMovie(@PathVariable String id) throws Exception {
		MovieRest returnValue = new MovieRest();
		
		MovieDto movieDto = movieService.getMovieById(id);
		BeanUtils.copyProperties(movieDto, returnValue);
		
		return returnValue;
	}
	
	@PostMapping
	public MovieRest createMovie(@RequestBody MovieDetailsRequestModel movieDetails) {
		MovieRest returnValue = new MovieRest();
		
		if(movieDetails.getTitle().isEmpty()
				|| movieDetails.getImage().isEmpty()) {
			throw new MovieServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		MovieDto movieDto = new MovieDto();
		BeanUtils.copyProperties(movieDetails, movieDto);
		
		MovieDto createdMovie = movieService.createMovie(movieDto);
		BeanUtils.copyProperties(createdMovie, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteMovie(@PathVariable String id) {		
		movieService.deleteMovie(id);
	}
}
