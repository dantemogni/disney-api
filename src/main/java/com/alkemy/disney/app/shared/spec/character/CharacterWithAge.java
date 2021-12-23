package com.alkemy.disney.app.shared.spec.character;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alkemy.disney.app.io.entity.CharacterEntity;

import lombok.Getter;
import lombok.Setter;

public class CharacterWithAge implements Specification<CharacterEntity> {

	private static final long serialVersionUID = -1147314628936999150L;
	
	@Getter
	@Setter
	private Integer age;
	
	public CharacterWithAge(Integer age) {
		super();
		this.age = age;
	}

	@Override
	public Predicate toPredicate(Root<CharacterEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(age == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
		return criteriaBuilder.equal(root.get("age"), this.age);
	}

}
