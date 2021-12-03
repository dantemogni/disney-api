package com.alkemy.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCharacterRequest {
	@NotBlank(message = "An image is requiered")
	private String image;
	
	@NotBlank(message = "A name is requiered")
	private String name;
	
	@NotNull(message = "An age is requiered")
	private Integer age;
	
	@NotNull(message = "A weight is requiered")
	private Double weight;
	
	@NotBlank(message = "A s is requiered")
	private String story;
}
