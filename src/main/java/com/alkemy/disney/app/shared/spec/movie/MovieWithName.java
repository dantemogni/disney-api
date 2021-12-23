package com.alkemy.disney.app.shared.spec.movie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alkemy.disney.app.io.entity.MovieEntity;

import lombok.Getter;
import lombok.Setter;

public class MovieWithName implements Specification<MovieEntity> {

	private static final long serialVersionUID = 3327310491462434424L;

	@Getter
	@Setter
	private String name;
	
	public MovieWithName(String name) {
		super();
		this.name = name;
	}

	@Override
	public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(name == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
		return criteriaBuilder.equal(root.get("title"), this.name);
	}

}
