package com.alkemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.Character;
import com.alkemy.repository.CharacterRepository;

@Service
public class CharacterService {
	@Autowired
	CharacterRepository characterRepository;
	
	public List<Character> getAllCharacters() {
		return characterRepository.findAll();
	}
}
