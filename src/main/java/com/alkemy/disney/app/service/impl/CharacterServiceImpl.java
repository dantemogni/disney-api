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

import com.alkemy.disney.app.exceptions.CharacterServiceException;
import com.alkemy.disney.app.io.entity.CharacterEntity;
import com.alkemy.disney.app.io.entity.MovieEntity;
import com.alkemy.disney.app.io.repository.CharacterRepository;
import com.alkemy.disney.app.io.repository.MovieRepository;
import com.alkemy.disney.app.service.CharacterService;
import com.alkemy.disney.app.shared.Utils;
import com.alkemy.disney.app.shared.dto.CharacterDto;
import com.alkemy.disney.app.shared.dto.MovieDto;
import com.alkemy.disney.app.shared.spec.character.CharacterWithAge;
import com.alkemy.disney.app.shared.spec.character.CharacterWithMovie;
import com.alkemy.disney.app.shared.spec.character.CharacterWithName;

@Service
public class CharacterServiceImpl implements CharacterService{
	@Autowired
	CharacterRepository characterRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public CharacterDto createCharacter(CharacterDto character) {
		ModelMapper modelMapper = new ModelMapper();
		CharacterEntity characterEntity = modelMapper.map(character, CharacterEntity.class);
				
		characterEntity.setCharacterId(utils.generateUserId(30));
		
		if(!character.getLinkedMovies().isEmpty()) {
			List<MovieEntity> aux = new ArrayList<MovieEntity>();
			
			for(MovieEntity movie : character.getLinkedMovies()) {	
				MovieEntity movieToAdd = movieRepository.findByMovieId(movie.getMovieId());	
				
				if(movieToAdd != null) aux.add(movieToAdd);
				
				characterEntity.setLinkedMovies(aux);
			}	
		}
		
		CharacterEntity storedCharacterDetails = characterRepository.save(characterEntity);
		CharacterDto returnValue = modelMapper.map(storedCharacterDetails, CharacterDto.class);
		
		return returnValue;
	}

	@Override
	public CharacterDto getCharacterById(String id) {
		CharacterEntity characterEntity = characterRepository.findByCharacterId(id);
		
		if(characterEntity == null) throw new CharacterServiceException("Character not found");
		
		CharacterDto returnValue = new CharacterDto();
		BeanUtils.copyProperties(characterEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public void deleteCharacter(String characterId) {
		CharacterEntity characterEntity = characterRepository.findByCharacterId(characterId);
		if(characterEntity == null) throw new CharacterServiceException("Character with ID:" + characterId + " not found");

		characterRepository.delete(characterEntity);		
	}

	@Override
	public CharacterDto updateCharacter(String id, CharacterDto character) {
		CharacterDto returnValue = new CharacterDto();

		CharacterEntity characterEntity = characterRepository.findByCharacterId(id);
		if(characterEntity == null) throw new CharacterServiceException("Character with ID:" + id + " not found");

		if(!(character.getName() == null)) characterEntity.setName(character.getName());
		if(!(character.getImage() == null)) characterEntity.setImage(character.getImage());
		if(!(character.getStory() == null)) characterEntity.setStory(character.getStory());
		if(!(character.getWeight() == 0)) characterEntity.setWeight(character.getWeight());
		if(!(character.getAge() == 0)) characterEntity.setAge(character.getAge());

		CharacterEntity updatedCharacterDetails = characterRepository.save(characterEntity);
		
		BeanUtils.copyProperties(updatedCharacterDetails, returnValue);

		return returnValue;
	}
	@Override
	public List<CharacterDto> getCharacters(String name, Integer age, String movieId, int page, int limit) {
		List<CharacterDto> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		List<CharacterEntity> characters = new ArrayList<>();
		
		// Trae la pelicula del query
		MovieDto movieDto = new MovieDto();
		if(movieId != null) {
			MovieEntity movieInCharacter = movieRepository.findByMovieId(movieId);
			
			//TODO: Manejar la excepcion
			if(movieInCharacter == null) throw new CharacterServiceException("Movie not found");
			
			BeanUtils.copyProperties(movieInCharacter, movieDto);
		}

		// Hace el filtrado de queries
		Specification<CharacterEntity> spec = Specification
				.where(new CharacterWithName(name))
				.and(new CharacterWithAge(age))
				.and(new CharacterWithMovie(movieDto));

		Page<CharacterEntity> charactersPage = characterRepository.findAll(spec, pageableRequest);
		characters = charactersPage.getContent();
		
		for(CharacterEntity characterEntity : characters) {
			CharacterDto characterDto = new CharacterDto();
			BeanUtils.copyProperties(characterEntity, characterDto);
			returnValue.add(characterDto);
		}
		
		return returnValue;
	}
}
