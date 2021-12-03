package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.entity.Movie;
import com.alkemy.request.CreateMovieRequest;
import com.alkemy.request.UpdateMovieRequest;
import com.alkemy.response.MovieResponse;
import com.alkemy.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@GetMapping()
	public List<MovieResponse> getAllCharacters() {
		List<Movie> movieList = movieService.getAllMovies();
		List<MovieResponse> movieResponseList = new ArrayList<MovieResponse>();
		
		movieList.stream().forEach(movie -> {
			movieResponseList.add(new MovieResponse(movie));
		});
		
		return movieResponseList;
	}
	
	@GetMapping(params = "title")
	public List<MovieResponse> getByName(@RequestParam String title) {
		List<Movie> movieList = movieService.getByTitle(title);
		List<MovieResponse> movieResponseList = new ArrayList<MovieResponse>();
		
		movieList.stream().forEach(movie -> {
			movieResponseList.add(new MovieResponse(movie));
		});
	
		return movieResponseList;
	}


	@PostMapping("create")
	public MovieResponse createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
		Movie movie = movieService.createMovie(createMovieRequest);
		return new MovieResponse(movie);
	}
	
	@PutMapping("update")
	public MovieResponse updateMovie(@Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
		Movie movie = movieService.updateMovie(updateMovieRequest);
		return new MovieResponse(movie);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteMovie(@PathVariable Long id) {
		return movieService.deleteMovie(id);
	}
}
