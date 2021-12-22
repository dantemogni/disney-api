package com.alkemy.disney.app.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.app.io.entity.GenreEntity;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<GenreEntity, Long>{
	GenreEntity findByGenreId(String id);
}
