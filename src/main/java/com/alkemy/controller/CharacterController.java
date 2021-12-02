package com.alkemy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.response.CharacterResponse;

@RestController
@RequestMapping("/characters")
public class CharacterController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@GetMapping()
	public CharacterResponse getCharacter() {
		return new CharacterResponse(1, "Test", "Yellow");
	}
}
