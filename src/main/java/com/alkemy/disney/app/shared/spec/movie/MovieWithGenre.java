package com.alkemy.disney.app.shared.spec.movie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alkemy.disney.app.io.entity.GenreEntity;
import com.alkemy.disney.app.io.entity.MovieEntity;
import com.alkemy.disney.app.shared.dto.GenreDto;

import lombok.Getter;
import lombok.Setter;

public class MovieWithGenre implements Specification<MovieEntity> {

	private static final long serialVersionUID = 8136789540631487413L;
	
	@Getter
	@Setter
	private GenreDto genre;

	public MovieWithGenre(GenreDto genre) {
		super();
		this.genre = genre;
	}

	@Override
	public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	
		if(genre == null || genre.getGenreId() == null || genre.getId() == null ) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
		
		return criteriaBuilder.equal(joinGenreId(root).get("genreId"), this.genre.getGenreId());
	}
	
    private ListJoin<MovieEntity, GenreEntity> joinGenreId(Root<MovieEntity> root){
        return root.joinList("linkedGenres", JoinType.INNER);
    }

}
