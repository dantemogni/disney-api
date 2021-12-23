package com.alkemy.disney.app.shared.spec.character;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alkemy.disney.app.io.entity.CharacterEntity;
import com.alkemy.disney.app.io.entity.MovieEntity;
import com.alkemy.disney.app.shared.dto.MovieDto;

import lombok.Getter;
import lombok.Setter;

public class CharacterWithMovie implements Specification<CharacterEntity> {

	private static final long serialVersionUID = 8136789540631487413L;
	@Getter
	@Setter
	private MovieDto movie;

	public CharacterWithMovie(MovieDto movie) {
		super();
		this.movie = movie;
	}

	@Override
	public Predicate toPredicate(Root<CharacterEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	
		if(movie == null || movie.getMovieId() == null || movie.getId() == null ) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}
		
		return criteriaBuilder.equal(joinMovieId(root).get("movieId"), this.movie.getMovieId());
	}
	
    private ListJoin<CharacterEntity, MovieEntity> joinMovieId(Root<CharacterEntity> root){
        return root.joinList("linkedMovies", JoinType.INNER);
    }

}
