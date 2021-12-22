package com.alkemy.disney.app.io.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "characters")
public class CharacterEntity implements Serializable {

	private static final long serialVersionUID = -727357667470251549L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String characterId;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String image;
	
	@Getter
	@Setter
	@Column(nullable = false, length = 50)
	private String name;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private int age;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private double weight;
	
	@Getter
	@Setter
	@Column(nullable = false)
	private String story;
	
	@Getter
	@Setter
	@JoinTable(
			name = "movie_has_character", 
			joinColumns = @JoinColumn(name = "character_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "movie_id", nullable = false)
			)
	@ManyToMany(targetEntity = MovieEntity.class, 
			cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            })
	private Set<MovieEntity> linkedMovies = new HashSet<MovieEntity>();
}
