package com.alkemy.disney.app.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.disney.app.io.entity.CharacterEntity;
import com.alkemy.disney.app.io.repository.CharacterRepository;
import com.alkemy.disney.app.service.CharacterService;
import com.alkemy.disney.app.shared.Utils;
import com.alkemy.disney.app.shared.dto.CharacterDto;

@Service
public class CharacterServiceImpl implements CharacterService{
	@Autowired
	CharacterRepository characterRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public CharacterDto createCharacter(CharacterDto character) {
		CharacterEntity characterEntity = new CharacterEntity();
		BeanUtils.copyProperties(character, characterEntity);
		
		characterEntity.setCharacterId(utils.generateUserId(30));
		
		CharacterEntity storedCharacterDetails = characterRepository.save(characterEntity);
		
		CharacterDto returnValue = new CharacterDto();
		BeanUtils.copyProperties(storedCharacterDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public CharacterDto getCharacterById(String id) throws Exception {
		CharacterEntity characterEntity = characterRepository.findByCharacterId(id);
		
		if(characterEntity == null) throw new Exception("Character not found");
		
		CharacterDto returnValue = new CharacterDto();
		BeanUtils.copyProperties(characterEntity, returnValue);
		
		return returnValue;
	}

}
