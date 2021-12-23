package com.alkemy.disney.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alkemy.disney.app.exceptions.GenreServiceException;
import com.alkemy.disney.app.exceptions.MovieServiceException;
import com.alkemy.disney.app.io.entity.GenreEntity;
import com.alkemy.disney.app.io.entity.MovieEntity;
import com.alkemy.disney.app.io.repository.GenreRepository;
import com.alkemy.disney.app.io.repository.MovieRepository;
import com.alkemy.disney.app.service.MovieService;
import com.alkemy.disney.app.shared.Utils;
import com.alkemy.disney.app.shared.dto.GenreDto;
import com.alkemy.disney.app.shared.dto.MovieDto;
import com.alkemy.disney.app.shared.spec.movie.MovieWithGenre;
import com.alkemy.disney.app.shared.spec.movie.MovieWithName;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	GenreRepository genreRespository;
	
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
		ModelMapper modelMapper = new ModelMapper();
		MovieEntity movieEntity = modelMapper.map(movie, MovieEntity.class);
		
		movieEntity.setMovieId(utils.generateUserId(30));
		
		if(!movie.getLinkedGenres().isEmpty()) {
			List<GenreEntity> aux = new ArrayList<GenreEntity>();
			
			for(GenreEntity genre : movie.getLinkedGenres()) {	
				GenreEntity genreToAdd = genreRespository.findByGenreId(genre.getGenreId());	
				
				if(genreToAdd != null) aux.add(genreToAdd);
				
				movieEntity.setLinkedGenres(aux);
			}	
		}
		
		MovieEntity storedMovieDetails = movieRepository.save(movieEntity);
		MovieDto returnValue = modelMapper.map(storedMovieDetails, MovieDto.class);
		
		return returnValue;
	}

	@Override
	public void deleteMovie(String movieId) {
		MovieEntity movieEntity = movieRepository.findByMovieId(movieId);
				
		if(movieEntity == null) throw new MovieServiceException("Movie with ID:" + movieId + " not found");

		movieRepository.delete(movieEntity);	
	}

	@Override
	public MovieDto updateMovie(String id, MovieDto movie) {
		MovieDto returnValue = new MovieDto();

		MovieEntity movieEntity = movieRepository.findByMovieId(id);
		if(movieEntity == null) throw new MovieServiceException("Movie with ID:" + id + " not found");

		if(!(movie.getTitle() == null)) movieEntity.setTitle(movie.getTitle());
		if(!(movie.getImage() == null)) movieEntity.setImage(movie.getImage());

		MovieEntity updatedMovieDetails = movieRepository.save(movieEntity);
		
		BeanUtils.copyProperties(updatedMovieDetails, returnValue);

		return returnValue;
	}

	@Override
	public List<MovieDto> getMovies(String name, String genreId, int page, int limit) {
		List<MovieDto> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		// Trae el genero del query
		GenreDto genreDto = new GenreDto();
		if(genreId != null) {
			GenreEntity genreInMovie = genreRespository.findByGenreId(genreId);
			
			//TODO: Manejar la excepcion
			if(genreInMovie == null) throw new GenreServiceException("Could not find genre");
			
			BeanUtils.copyProperties(genreInMovie, genreDto);
		}
		
		// Hace el filtrado de queries
		Specification<MovieEntity> spec = Specification
						.where(new MovieWithName(name))
						.and(new MovieWithGenre(genreDto));
		
		
		Page<MovieEntity> moviesPage = movieRepository.findAll(spec, pageableRequest);
		List<MovieEntity> movies = moviesPage.getContent();
		
		for(MovieEntity movieEntity : movies) {
			MovieDto movieDto = new MovieDto();
			BeanUtils.copyProperties(movieEntity, movieDto);
			returnValue.add(movieDto);
		}
		
		return returnValue;
	}
}
