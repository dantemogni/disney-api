package com.alkemy.disney.app.io.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate creationDate;
	

	@ManyToMany(targetEntity = CharacterEntity.class, mappedBy = "linkedMovies")
	private List<CharacterEntity> characters = new ArrayList<CharacterEntity>();

	@JoinTable(
			name = "movie_has_genre", 
			joinColumns = @JoinColumn(name = "movie_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false)
			)
	@ManyToMany(targetEntity = GenreEntity.class, 
			cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })
	private List<GenreEntity> linkedGenres = new ArrayList<GenreEntity>();
	
	@JsonBackReference
	public List<CharacterEntity> getCharacters() {
		return characters;
	}

	public void setCharacters(List<CharacterEntity> characters) {
		this.characters = characters;
	}
	
	@JsonManagedReference
	public List<GenreEntity> getLinkedGenres() {
		return linkedGenres;
	}

	public void setLinkedGenres(List<GenreEntity> linkedGenres) {
		this.linkedGenres = linkedGenres;
	}
	
}
