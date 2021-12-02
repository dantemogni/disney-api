package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.entity.Character;
import com.alkemy.response.CharacterResponse;
import com.alkemy.service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
	@Autowired
	CharacterService characterService;
	
	@GetMapping("/getAll")
	public List<CharacterResponse> getAllCharacters() {
		List<Character> characterList = characterService.getAllCharacters();
		List<CharacterResponse> characterResponseList = new ArrayList<CharacterResponse>();
		
		characterList.stream().forEach(character -> {
			characterResponseList.add(new CharacterResponse(character));
		});
		
		return characterResponseList;
	}
}
