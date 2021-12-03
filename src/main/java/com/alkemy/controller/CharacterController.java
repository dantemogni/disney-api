package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.entity.Character;
import com.alkemy.request.CreateCharacterRequest;
import com.alkemy.request.UpdateCharacterRequest;
import com.alkemy.response.CharacterResponse;
import com.alkemy.service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
	@Autowired
	CharacterService characterService;
	
	@GetMapping()
	public List<CharacterResponse> getAllCharacters() {
		List<Character> characterList = characterService.getAllCharacters();
		List<CharacterResponse> characterResponseList = new ArrayList<CharacterResponse>();
		
		characterList.stream().forEach(character -> {
			characterResponseList.add(new CharacterResponse(character));
		});
		
		return characterResponseList;
	}
	
	@GetMapping(params = "name")
	public List<CharacterResponse> getByName(@RequestParam String name) {
		List<Character> characterList = characterService.getByName(name);
		List<CharacterResponse> characterResponseList = new ArrayList<CharacterResponse>();
		
		characterList.stream().forEach(character -> {
			characterResponseList.add(new CharacterResponse(character));
		});
		
		return characterResponseList;
	}


	@PostMapping("create")
	public CharacterResponse createCharacter(@Valid @RequestBody CreateCharacterRequest createCharacterRequest) {
		Character character = characterService.createCharacter(createCharacterRequest);
		return new CharacterResponse(character);
	}
	
	@PutMapping("update")
	public CharacterResponse updateStudent(@Valid @RequestBody UpdateCharacterRequest updateCharacterRequest) {
		Character character = characterService.updateCharacter(updateCharacterRequest);
		return new CharacterResponse(character);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteCharacter(@PathVariable Long id) {
		return characterService.deleteCharacter(id);
	}
}
