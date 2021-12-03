package com.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	public List<Movie> findByTitle(String title);

}
