/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sapient.theatre.exceptions.CommonAPIException;
import com.sapient.theatre.models.ShowDTO;
import com.sapient.theatre.models.ShowSeatsDTO;
import com.sapient.theatre.models.TheatreDTO;

/**
 * The Interface TheatreService.
 * @author Sandeep Kumar
 */
public interface TheatreService {

	/**
	 * Creates the.
	 *
	 * @param theatre the theatre
	 * @return the theatre DTO
	 * @throws CommonAPIException the common API exception
	 */
	TheatreDTO create(TheatreDTO theatre) throws CommonAPIException;
	
	/**
	 * Update.
	 *
	 * @param theatre the theatre
	 * @return the theatre DTO
	 * @throws CommonAPIException the common API exception
	 */
	TheatreDTO update(TheatreDTO theatre) throws CommonAPIException;
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the theatre DTO
	 * @throws CommonAPIException the common API exception
	 */
	TheatreDTO delete(Long id) throws CommonAPIException;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws CommonAPIException the common API exception
	 */
	TheatreDTO getById(Long id) throws CommonAPIException;
	
	/**
	 * Gets the all.
	 *
	 * @param query    the query
	 * @param pageable the pageable
	 * @return the all
	 */
	Page<TheatreDTO> getAll(String query, Pageable pageable);

	/**
	 * Creates the show.
	 *
	 * @param show the show
	 * @return the theatre DTO
	 * @throws CommonAPIException the common API exception
	 */
	TheatreDTO createShow(ShowDTO show) throws CommonAPIException;
	
	/**
	 * Delete show.
	 *
	 * @param id the id
	 * @return the theatre DTO
	 * @throws CommonAPIException the common API exception
	 */
	TheatreDTO deleteShow(Long id) throws CommonAPIException;
	
	/**
	 * Udpate show.
	 *
	 * @param dto the dto
	 * @return the show seats DTO
	 * @throws CommonAPIException the common API exception
	 */
	ShowSeatsDTO udpateShow(ShowSeatsDTO dto) throws CommonAPIException;
}
