/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.utils;

import java.util.HashSet;
import java.util.Set;

import com.sapient.booking.entities.Seat;

/**
 * The Class TheatreUtils.
 * @author Sandeep Kumars
 */
public final class TheatreUtils {

	/**
	 * Instantiates a new theatre utils.
	 */
	private TheatreUtils() {}
	
	/**
	 * Generate seats.
	 *
	 * @param row    the row
	 * @param column the column
	 * @return the sets the
	 */
	public static Set<Seat> generateSeats(int row, int column) {
		Set<Seat> seats = new HashSet<>();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				Seat seat = new Seat();
				seat.setBooked(false);
				seat.setCode(i + "_" + j);
				seats.add(seat);
			}
		}
		return seats;
	}
	
}
