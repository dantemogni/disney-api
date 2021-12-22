package com.alkemy.disney.app.io.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "movies")
@JsonIgnoreProperties("id")
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = -3789037636323586802L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String movieId;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String image;
	
	@Getter
	@Setter
	@Column(nullable = false, length = 100)
	private String title;
	
	@Getter
	@Setter
	@Column
	private int rating;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private LocalDate creationDate;
	

	@ManyToMany(targetEntity = CharacterEntity.class, mappedBy = "linkedMovies")
	private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();

	@JsonBackReference
	public Set<CharacterEntity> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<CharacterEntity> characters) {
		this.characters = characters;
	}
	
}
