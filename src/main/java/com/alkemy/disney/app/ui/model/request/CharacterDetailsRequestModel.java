package com.alkemy.disney.app.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDetailsRequestModel {
	private String name, image, story;
	private int age;
	private double weight;
}
