package com.alkemy.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCharacterRequest {
	@NotNull(message = "Character ID is requiered")
	private Long id;
	private String image;
	private String name;
	private Integer age;
	private Double weight;
	private String story;
}
