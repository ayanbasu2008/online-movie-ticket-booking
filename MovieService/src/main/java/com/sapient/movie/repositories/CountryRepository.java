/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.movie.entities.Country;

/**
 * The Interface CountryRepository.
 * @author Sandeep Kumar
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}
