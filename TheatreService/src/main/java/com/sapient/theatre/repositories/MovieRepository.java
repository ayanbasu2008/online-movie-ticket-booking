/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sapient.theatre.entities.Movie;

/**
 * The Interface MovieRepository.
 * @author Sandeep Kumar
 */
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

	/**
	 * Checks if is movie by id.
	 *
	 * @param id the id
	 * @return true, if is movie by id
	 */
	@Query("select case when count(m) > 0 then true else false end from Movie m where m.id = ?1")
	boolean isMovieById(Long id);
	
	/**
	 * Checks if is movie by name and year.
	 *
	 * @param name the name
	 * @param year the year
	 * @return true, if is movie by name and year
	 */
	@Query("select case when count(m) > 0 then true else false end from Movie m where m.name = ?1 and m.year = ?2")
	boolean isMovieByNameAndYear(String name, Integer year);
	
	/**
	 * Checks if is movie by name and year without same id.
	 *
	 * @param id   the id
	 * @param name the name
	 * @param year the year
	 * @return true, if is movie by name and year without same id
	 */
	@Query("select case when count(m) > 0 then true else false end from Movie m where m.id <> ?1 and m.name = ?2 and m.year = ?3")
	boolean isMovieByNameAndYearWithoutSameId(Long id, String name, Integer year);
}
