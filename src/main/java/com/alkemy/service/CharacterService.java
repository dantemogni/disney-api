package com.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.Character;
import com.alkemy.repository.CharacterRepository;
import com.alkemy.request.CreateCharacterRequest;
import com.alkemy.request.UpdateCharacterRequest;

@Service
public class CharacterService {
	
	@Autowired
	CharacterRepository characterRepository;
	
	public List<Character> getAllCharacters() {
		return characterRepository.findAll();
	}
	
	public Character createCharacter(CreateCharacterRequest createCharacterRequest) {
		Character character = new Character(createCharacterRequest);
		return characterRepository.save(character);
	}
	
	public Character updateCharacter(UpdateCharacterRequest updateCharacterRequest) {
		Character character = characterRepository.findById(updateCharacterRequest.getId()).get();
		
		if(updateCharacterRequest.getName() != null && !updateCharacterRequest.getName().isEmpty()) {
			character.setName(updateCharacterRequest.getName());
		}
		
		if(updateCharacterRequest.getAge() != null) {
			character.setAge(updateCharacterRequest.getAge());
		}
		
		if(updateCharacterRequest.getImage() != null && !updateCharacterRequest.getImage().isEmpty()) {
			character.setImage(updateCharacterRequest.getImage());
		}
		
		if(updateCharacterRequest.getStory() != null && !updateCharacterRequest.getStory().isEmpty()) {
			character.setStory(updateCharacterRequest.getStory());
		}
		
		if(updateCharacterRequest.getWeight() != null) {
			character.setWeight(updateCharacterRequest.getWeight());
		}
		character = characterRepository.save(character);
		
		return character;
	}

	public String deleteCharacter(Long id) {
		characterRepository.deleteById(id);
		return "Character has been deleted";
	}
}
