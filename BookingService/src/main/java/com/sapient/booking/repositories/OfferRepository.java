/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.booking.entities.Offer;

/**
 * The Interface OfferRepository.
 * @author Sandeep Kumar
 */
public interface OfferRepository extends JpaRepository<Offer, Long> {

	/**
	 * Gets the offer by theatre id.
	 *
	 * @param id the id
	 * @return the offer by theatre id
	 */
	@Query("from Offer o join o.theatres t where t.id = ?1")
	Set<Offer> getOfferByTheatreId(Long id);
}
