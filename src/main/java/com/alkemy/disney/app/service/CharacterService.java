package com.alkemy.disney.app.service;

import java.util.List;

import com.alkemy.disney.app.shared.dto.CharacterDto;

public interface CharacterService {
	public CharacterDto getCharacterById(String id) throws Exception;
	public void deleteCharacter(String characterId);
	public CharacterDto createCharacter(CharacterDto character);
	public CharacterDto updateCharacter(String id, CharacterDto characterDto);
	public List<CharacterDto> getCharacters(String name, Integer age, String movieId, int page, int limit);
}
