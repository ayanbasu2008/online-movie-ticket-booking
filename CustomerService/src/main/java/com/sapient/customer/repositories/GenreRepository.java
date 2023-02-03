/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.customer.entities.Genre;

/**
 * The Interface GenreRepository.
 * @author Sandeep Kumar
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {
	
	/**
	 * Gets the genre count by name list.
	 *
	 * @param names the names
	 * @return the genre count by name list
	 */
	@Query("select count(g) from Genre g where g.name in (?1)")
	int getGenreCountByNameList(Set<String> names);

	/**
	 * Gets the genre by name list.
	 *
	 * @param names the names
	 * @return the genre by name list
	 */
	@Query("select g from Genre g where g.name in (?1)")
	Set<Genre> getGenreByNameList(Set<String> names);

}
