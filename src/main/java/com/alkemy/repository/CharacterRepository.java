package com.alkemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.entity.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
