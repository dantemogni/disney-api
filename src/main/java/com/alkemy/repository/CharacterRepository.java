package com.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.entity.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
	public List<Character> findByName(String name);
}
