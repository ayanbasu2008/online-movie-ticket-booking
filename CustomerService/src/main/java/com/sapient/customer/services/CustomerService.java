/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sapient.customer.exceptions.CommonAPIException;
import com.sapient.customer.models.CustomerDTO;

/**
 * The Interface CustomerService.
 * @author Sandeep Kumar
 */
public interface CustomerService {

	/**
	 * Creates the.
	 *
	 * @param customer the customer
	 * @return the customer DTO
	 * @throws CommonAPIException the common API exception
	 */
	CustomerDTO create(CustomerDTO customer) throws CommonAPIException;
	
	/**
	 * Update.
	 *
	 * @param customer the customer
	 * @return the customer DTO
	 * @throws CommonAPIException the common API exception
	 */
	CustomerDTO update(CustomerDTO customer) throws CommonAPIException;
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the customer DTO
	 * @throws CommonAPIException the common API exception
	 */
	CustomerDTO delete(Long id) throws CommonAPIException;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws CommonAPIException the common API exception
	 */
	CustomerDTO getById(Long id) throws CommonAPIException;
	
	/**
	 * Gets the all.
	 *
	 * @param query    the query
	 * @param pageable the pageable
	 * @return the all
	 */
	Page<CustomerDTO> getAll(String query, Pageable pageable);

}
