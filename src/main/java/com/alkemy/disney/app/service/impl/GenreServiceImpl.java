package com.alkemy.disney.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.disney.app.exceptions.CharacterServiceException;
import com.alkemy.disney.app.exceptions.GenreServiceException;
import com.alkemy.disney.app.io.entity.GenreEntity;
import com.alkemy.disney.app.io.repository.GenreRepository;
import com.alkemy.disney.app.service.GenreService;
import com.alkemy.disney.app.shared.Utils;
import com.alkemy.disney.app.shared.dto.GenreDto;

@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public GenreDto getGenreById(String id) {
		GenreEntity genreEntity = genreRepository.findByGenreId(id);
		
		if(genreEntity == null) throw new CharacterServiceException("Genre not found");
		
		GenreDto returnValue = new GenreDto();
		BeanUtils.copyProperties(genreEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteGenre(String id) {
		GenreEntity genreEntity = genreRepository.findByGenreId(id);
		if(genreEntity == null) throw new GenreServiceException("Genre with ID:" + id + " not found");

		genreRepository.delete(genreEntity);		
	}

	@Override
	public GenreDto createGenre(GenreDto genre) {
		GenreEntity genreEntity = new GenreEntity();
		BeanUtils.copyProperties(genre, genreEntity);
		
		genreEntity.setGenreId(utils.generateUserId(30));
		
		GenreEntity storedGenreDetails = genreRepository.save(genreEntity);
		
		GenreDto returnValue = new GenreDto();
		BeanUtils.copyProperties(storedGenreDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public GenreDto updateGenre(String id, GenreDto genre) {
		GenreDto returnValue = new GenreDto();

		GenreEntity genreEntity = genreRepository.findByGenreId(id);
		if(genreEntity == null) throw new GenreServiceException("Genre with ID:" + id + " not found");

		if(!(genre.getName() == null)) genreEntity.setName(genre.getName());
		if(!(genre.getImage() == null)) genreEntity.setImage(genre.getImage());

		GenreEntity updatedGenreDetails = genreRepository.save(genreEntity);
		
		BeanUtils.copyProperties(updatedGenreDetails, returnValue);

		return returnValue;
	}

	@Override
	public List<GenreDto> getGenres(int page, int limit) {
		List<GenreDto> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<GenreEntity> genresPage = genreRepository.findAll(pageableRequest);
		List<GenreEntity> genres = genresPage.getContent();
		
		for(GenreEntity genreEntity : genres) {
			GenreDto genreDto = new GenreDto();
			BeanUtils.copyProperties(genreEntity, genreDto);
			returnValue.add(genreDto);
		}
		
		return returnValue;
	}

}
