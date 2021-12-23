package com.alkemy.disney.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.alkemy.disney.app.exceptions.GenreServiceException;
import com.alkemy.disney.app.service.GenreService;
import com.alkemy.disney.app.shared.dto.GenreDto;
import com.alkemy.disney.app.ui.model.request.GenreDetailsRequestModel;
import com.alkemy.disney.app.ui.model.response.ErrorMessages;
import com.alkemy.disney.app.ui.model.response.GenreRest;

@RestController
@RequestMapping("genres")
public class GenreController {
	
	@Autowired
	GenreService genreService;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<GenreRest> getGenre(@PathVariable String id) throws Exception {
		GenreRest returnValue = new GenreRest();
		
		GenreDto genreDto = genreService.getGenreById(id);
		BeanUtils.copyProperties(genreDto, returnValue);
		
		return new ResponseEntity<GenreRest>(returnValue, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<GenreRest>> getGenres(@RequestParam(value="page", defaultValue="0") int page,
								   @RequestParam(value="limit", defaultValue="25") int limit) {
		List<GenreRest> returnValue = new ArrayList<>();
		
		List<GenreDto> genres = genreService.getGenres(page, limit);
		
		for(GenreDto genreDto : genres) {
			GenreRest genreModel = new GenreRest();
			BeanUtils.copyProperties(genreDto, genreModel);
			returnValue.add(genreModel);
		}
		
		return new ResponseEntity<List<GenreRest>>(returnValue, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<GenreRest> createGenre(@RequestBody GenreDetailsRequestModel genreDetails) {
		GenreRest returnValue = new GenreRest();
		
		if(genreDetails.getName().isEmpty()
				|| genreDetails.getImage().isEmpty()) {
			throw new GenreServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genreDetails, genreDto);
		
		GenreDto createdGenre = genreService.createGenre(genreDto);
		BeanUtils.copyProperties(createdGenre, returnValue);
		
		return new ResponseEntity<GenreRest>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<GenreRest> updateGenre(@PathVariable String id, @RequestBody GenreDetailsRequestModel genreDetails) {
		GenreRest returnValue = new GenreRest();

		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genreDetails, genreDto);
		
		GenreDto updatedGenre = genreService.updateGenre(id, genreDto);
		BeanUtils.copyProperties(updatedGenre, returnValue);
		
		return new ResponseEntity<GenreRest>(returnValue, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteGenre(@PathVariable String id) {		
		genreService.deleteGenre(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
