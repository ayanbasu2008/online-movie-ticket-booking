/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sapient.movie.entities.Theatre;

/**
 * The Interface TheatreRepository.
 * @author Sandeep Kumar
 */
public interface TheatreRepository extends JpaRepository<Theatre, Long>, JpaSpecificationExecutor<Theatre> {
	
	/**
	 * Checks if is theatre by name and city.
	 *
	 * @param name the name
	 * @param city the city
	 * @return true, if is theatre by name and city
	 */
	@Query("select case when count(t) > 0 then true else false end from Theatre t where t.name = ?1 and t.city = ?2")
	boolean isTheatreByNameAndCity(String name, String city);

	/**
	 * Gets the theatre by name and city.
	 *
	 * @param name the name
	 * @param city the city
	 * @return the theatre by name and city
	 */
	@Query("select t from Theatre t where t.name = ?1 and t.city = ?2")
	Theatre getTheatreByNameAndCity(String name, String city);

	/**
	 * Checks if is theatre by name and city except id.
	 *
	 * @param name the name
	 * @param city the city
	 * @param id   the id
	 * @return true, if is theatre by name and city except id
	 */
	@Query("select case when count(t) > 0 then true else false end from Theatre t where t.name = ?1 and t.city = ?2 and t.id <> ?3")
	boolean isTheatreByNameAndCityExceptId(String name, String city, Long id);

	/**
	 * Checks if is theatre by id.
	 *
	 * @param id the id
	 * @return true, if is theatre by id
	 */
	@Query("select case when count(t) > 0 then true else false end from Theatre t where t.id = ?1")
	boolean isTheatreById(Long id);

}
