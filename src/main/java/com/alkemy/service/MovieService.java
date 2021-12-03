package com.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.Movie;
import com.alkemy.repository.MovieRepository;
import com.alkemy.request.CreateMovieRequest;
import com.alkemy.request.UpdateMovieRequest;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public Movie createMovie(CreateMovieRequest createMovieRequest) {
		Movie movie = new Movie(createMovieRequest);
		return movieRepository.save(movie);
	}
	
	public Movie updateMovie(UpdateMovieRequest updateMovieRequest) {
		Movie movie = movieRepository.findById(updateMovieRequest.getId()).get();
		
		if(updateMovieRequest.getTitle() != null && !updateMovieRequest.getTitle().isEmpty()) {
			movie.setTitle(updateMovieRequest.getTitle());
		}
		
		if(updateMovieRequest.getImage() != null && !updateMovieRequest.getImage().isEmpty()) {
			movie.setImage(updateMovieRequest.getImage());
		}
		
		if(updateMovieRequest.getRating() != null) {
			movie.setRating(updateMovieRequest.getRating());
		}
		
		movie = movieRepository.save(movie);
		
		return movie;
	}

	public String deleteMovie(Long id) {
		movieRepository.deleteById(id);
		return "Movie has been deleted";
	}
	public List<Movie> getByTitle(String title){
		return movieRepository.findByTitle(title);
	}
}
