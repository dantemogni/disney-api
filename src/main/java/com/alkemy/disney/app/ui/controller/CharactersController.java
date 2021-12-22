package com.alkemy.disney.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.alkemy.disney.app.exceptions.CharacterServiceException;
import com.alkemy.disney.app.service.CharacterService;
import com.alkemy.disney.app.shared.dto.CharacterDto;
import com.alkemy.disney.app.ui.model.request.CharacterDetailsRequestModel;
import com.alkemy.disney.app.ui.model.response.CharacterDetailsRest;
import com.alkemy.disney.app.ui.model.response.CharacterRest;
import com.alkemy.disney.app.ui.model.response.ErrorMessages;

@RestController
@RequestMapping("characters")
public class CharactersController {
	
	@Autowired
	CharacterService characterService;
	
	@GetMapping(path="/{id}")
	public CharacterRest getCharacter(@PathVariable String id) throws Exception {
		CharacterRest returnValue = new CharacterRest();
		
		CharacterDto characterDto = characterService.getCharacterById(id);
		BeanUtils.copyProperties(characterDto, returnValue);
		
		return returnValue;
	}
	
	@GetMapping(path="/details/{id}")
	public CharacterDetailsRest getCharacterDetails(@PathVariable String id) throws Exception {
		CharacterDetailsRest returnValue = new CharacterDetailsRest();
		
		CharacterDto characterDto = characterService.getCharacterById(id);
		BeanUtils.copyProperties(characterDto, returnValue);
		
		return returnValue;
	}
	
	@GetMapping
	public List<CharacterRest> getCharacters(@RequestParam(value="page", defaultValue="0") int page,
								   @RequestParam(value="limit", defaultValue="25") int limit) {
		List<CharacterRest> returnValue = new ArrayList<>();
		
		List<CharacterDto> characters = characterService.getCharacters(page, limit);
		
		for(CharacterDto characterDto : characters) {
			CharacterRest characterModel = new CharacterRest();
			BeanUtils.copyProperties(characterDto, characterModel);
			returnValue.add(characterModel);
		}
		
		return returnValue;
		
	}
	
	@PostMapping
	public CharacterRest createCharacter(@RequestBody CharacterDetailsRequestModel characterDetails) {
		CharacterRest returnValue = new CharacterRest();
		
		if(characterDetails.getName().isEmpty()
				|| characterDetails.getImage().isEmpty()
				|| characterDetails.getAge() <= 0
				|| characterDetails.getStory().isEmpty()
				|| characterDetails.getWeight() <= 0) {
			throw new CharacterServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		CharacterDto characterDto = new CharacterDto();
		BeanUtils.copyProperties(characterDetails, characterDto);
		
		CharacterDto createdCharacter = characterService.createCharacter(characterDto);
		BeanUtils.copyProperties(createdCharacter, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}")
	public CharacterRest updateCharacter(@PathVariable String id, @RequestBody CharacterDetailsRequestModel characterDetails) {
		CharacterRest returnValue = new CharacterRest();

		CharacterDto characterDto = new CharacterDto();
		BeanUtils.copyProperties(characterDetails, characterDto);
		
		CharacterDto updatedCharacter = characterService.updateCharacter(id, characterDto);
		BeanUtils.copyProperties(updatedCharacter, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteCharacter(@PathVariable String id) {		
		characterService.deleteCharacter(id);
	}
}
