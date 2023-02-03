/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sapient.movie.exceptions.CommonAPIException;
import com.sapient.movie.models.MovieDTO;

/**
 * The Interface MovieService.
 * @author Sandeep Kumar
 */
public interface MovieService {

	/**
	 * Creates the.
	 *
	 * @param movie the movie
	 * @return the movie DTO
	 * @throws CommonAPIException the common API exception
	 */
	MovieDTO create(MovieDTO movie) throws CommonAPIException;
	
	/**
	 * Update.
	 *
	 * @param movie the movie
	 * @return the movie DTO
	 * @throws CommonAPIException the common API exception
	 */
	MovieDTO update(MovieDTO movie) throws CommonAPIException;
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the movie DTO
	 * @throws CommonAPIException the common API exception
	 */
	MovieDTO delete(Long id) throws CommonAPIException;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws CommonAPIException the common API exception
	 */
	MovieDTO getById(Long id) throws CommonAPIException;
	
	/**
	 * Gets the all.
	 *
	 * @param query    the query
	 * @param pageable the pageable
	 * @return the all
	 */
	Page<MovieDTO> getAll(String query, Pageable pageable);

}
