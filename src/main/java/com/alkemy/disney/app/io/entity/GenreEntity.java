package com.alkemy.disney.app.io.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "genres")
public class GenreEntity implements Serializable {

	private static final long serialVersionUID = 7208291533827731821L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String genreId;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String image;
	
	@Getter
	@Setter
	@Column(nullable = false, length = 50)
	private String name;
	
	@ManyToMany(targetEntity = MovieEntity.class, mappedBy = "linkedGenres")
	private List<MovieEntity> movies = new ArrayList<MovieEntity>();

	@JsonBackReference
	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}

	
}
