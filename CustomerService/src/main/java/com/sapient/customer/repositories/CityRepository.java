/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.customer.entities.City;

/**
 * The Interface CityRepository.
 * @author Sandeep Kumar
 */
public interface CityRepository extends JpaRepository<City, Long> {

	/**
	 * Checks if is city available.
	 *
	 * @param code the code
	 * @return true, if is city available
	 */
	@Query("select case when count(c) > 0 then true else false end from City c where c.code = ?1")
	boolean isCityAvailable(String code);
}
