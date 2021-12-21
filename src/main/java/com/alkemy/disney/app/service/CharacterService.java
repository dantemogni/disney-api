package com.alkemy.disney.app.service;

import com.alkemy.disney.app.shared.dto.CharacterDto;

public interface CharacterService {
	public CharacterDto getCharacterById(String id) throws Exception;

	public CharacterDto createCharacter(CharacterDto character);
}
