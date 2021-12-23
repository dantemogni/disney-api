package com.alkemy.disney.app.shared.spec.character;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alkemy.disney.app.io.entity.CharacterEntity;

import lombok.Getter;
import lombok.Setter;

public class CharacterWithName implements Specification<CharacterEntity> {

	private static final long serialVersionUID = -842826897018995190L;
	
	@Getter
	@Setter
	private String name;
	
	public CharacterWithName(String name) {
		super();
		this.name = name;
	}

	@Override
	public Predicate toPredicate(Root<CharacterEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(name == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
		return criteriaBuilder.equal(root.get("name"), this.name);
	}

}
