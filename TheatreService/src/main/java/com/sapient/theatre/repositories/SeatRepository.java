/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.theatre.entities.Seat;

/**
 * The Interface SeatRepository.
 * @author Sandeep Kumar
 */
public interface SeatRepository extends JpaRepository<Seat, Long> {

	/**
	 * Gets the seat by booking id.
	 *
	 * @param seats the seats
	 * @return the seat by booking id
	 */
	@Query("from Seat s where s.bookingId in (?1)")
	Set<Seat> getSeatByBookingId(List<Long> seats);
}
