package com.alkemy.disney.app.service;

import com.alkemy.disney.app.shared.dto.CharacterDto;

public interface CharacterService {
	public CharacterDto getCharacterById(String id) throws Exception;
	public void deleteCharacter(String characterId);
	public CharacterDto createCharacter(CharacterDto character);
	public CharacterDto updateCharacter(String id, CharacterDto characterDto);
}
