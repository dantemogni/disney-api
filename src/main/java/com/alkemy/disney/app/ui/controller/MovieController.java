package com.alkemy.disney.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.disney.app.exceptions.MovieServiceException;
import com.alkemy.disney.app.service.MovieService;
import com.alkemy.disney.app.shared.dto.MovieDto;
import com.alkemy.disney.app.ui.model.request.MovieDetailsRequestModel;
import com.alkemy.disney.app.ui.model.response.ErrorMessages;
import com.alkemy.disney.app.ui.model.response.MovieDetailsRest;
import com.alkemy.disney.app.ui.model.response.MovieRest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("movies")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@GetMapping(path="/{id}")
	public ResponseEntity<MovieRest> getMovie(@PathVariable String id) throws Exception {
		MovieRest returnValue = new MovieRest();
		
		MovieDto movieDto = movieService.getMovieById(id);
		BeanUtils.copyProperties(movieDto, returnValue);
		
		return new ResponseEntity<MovieRest>(returnValue, HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@GetMapping(path="/details/{id}")
	public ResponseEntity<MovieDetailsRest> getMovieDetails(@PathVariable String id) throws Exception {
		MovieDetailsRest returnValue = new MovieDetailsRest();
		
		MovieDto movieDto = movieService.getMovieById(id);
		BeanUtils.copyProperties(movieDto, returnValue);
		
		return new ResponseEntity<MovieDetailsRest>(returnValue, HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@GetMapping
	public ResponseEntity<List<MovieRest>> getMovies(
								@RequestParam(value="name",  required = false) String name,
								@RequestParam(value="genre",  required = false) String genreId,
								@RequestParam(value="order", defaultValue="ASC") String order,
								@RequestParam(value="page", defaultValue="0") int page,
								@RequestParam(value="limit", defaultValue="25") int limit) {
		List<MovieRest> returnValue = new ArrayList<>();
		
		List<MovieDto> movies = movieService.getMovies(name, genreId, order, page, limit);
		
		for(MovieDto movieDto : movies) {
			MovieRest movieModel = new MovieRest();
			BeanUtils.copyProperties(movieDto, movieModel);
			returnValue.add(movieModel);
		}
		
		return new ResponseEntity<List<MovieRest>>(returnValue, HttpStatus.OK);
		
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@PostMapping
	public ResponseEntity<MovieRest> createMovie(@RequestBody MovieDetailsRequestModel movieDetails) {
		MovieRest returnValue = new MovieRest();
		
		if(movieDetails.getTitle().isEmpty()
				|| movieDetails.getImage().isEmpty()) {
			throw new MovieServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		ModelMapper modelMapper = new ModelMapper();
		MovieDto movieDto = modelMapper.map(movieDetails, MovieDto.class);
		
		MovieDto createdMovie = movieService.createMovie(movieDto);
		returnValue = modelMapper.map(createdMovie, MovieRest.class);
		
		return new ResponseEntity<MovieRest>(returnValue, HttpStatus.CREATED);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@PutMapping(path="/{id}")
	public ResponseEntity<MovieRest> updateMovie(@PathVariable String id, @RequestBody MovieDetailsRequestModel movieDetails) {
		MovieRest returnValue = new MovieRest();

		MovieDto movieDto = new MovieDto();
		BeanUtils.copyProperties(movieDetails, movieDto);
		
		MovieDto updatedMovie = movieService.updateMovie(id, movieDto);
		BeanUtils.copyProperties(updatedMovie, returnValue);
		
		return new ResponseEntity<MovieRest>(returnValue, HttpStatus.OK);
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="authorization", value="Bearer JWT Token", paramType="header")
	})
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteMovie(@PathVariable String id) {		
		movieService.deleteMovie(id);		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
