package com.alkemy.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMovieRequest {
	@NotBlank(message = "An image is requiered")
	private String image;
	
	@NotBlank(message = "A title is requiered")
	private String title;
	
	@NotNull(message = "A rating is requiered")
	private Integer rating;
}
