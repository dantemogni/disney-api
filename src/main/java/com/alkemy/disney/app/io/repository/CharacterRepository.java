package com.alkemy.disney.app.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.app.io.entity.CharacterEntity;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Long>{
	CharacterEntity findByCharacterId(String id);
}
