package com.alkemy.disney.app.io.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.disney.app.io.entity.CharacterEntity;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity>{
	CharacterEntity findByCharacterId(String id);
	CharacterEntity findByName(String name);
	CharacterEntity findByAge(Integer age);
	CharacterEntity findByLinkedMovies(String id);


}
