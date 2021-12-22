package com.alkemy.disney.app.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.app.io.entity.MovieEntity;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long> {
	MovieEntity findByMovieId(String id);
}
