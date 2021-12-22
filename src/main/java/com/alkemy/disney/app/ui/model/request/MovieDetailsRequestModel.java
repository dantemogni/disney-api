package com.alkemy.disney.app.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRequestModel {
	private String image, title;
	private int rating;
}
