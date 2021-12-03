package com.alkemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alkemy.request.CreateMovieRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "rating")
	private Integer rating;
	
	public Movie(CreateMovieRequest createMovieRequest) {
		this.image = createMovieRequest.getImage();
		this.title = createMovieRequest.getTitle();
		this.rating = createMovieRequest.getRating();
	}

}
