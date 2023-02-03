/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.services;

import java.util.List;

import com.sapient.booking.exceptions.CommonAPIException;
import com.sapient.booking.models.BookingDetailDTO;
import com.sapient.booking.models.BookingRequestDTO;
import com.sapient.booking.models.TicketDTO;

/**
 * The Interface BookingService.
 * @author Sandeep Kumar
 */
public interface BookingService {
	
	/**
	 * Gets the by details.
	 *
	 * @param name  the name
	 * @param city  the city
	 * @param movie the movie
	 * @param date  the date
	 * @return the by details
	 */
	List<TicketDTO> getByDetails(String name, String city, String movie, String date);
	
	/**
	 * Booking.
	 *
	 * @param bookingRequests the booking requests
	 * @return the list
	 * @throws CommonAPIException the common API exception
	 */
	List<BookingDetailDTO> booking(List<BookingRequestDTO> bookingRequests) throws CommonAPIException;
	
	/**
	 * Booking cancel.
	 *
	 * @param bookingIds the booking ids
	 * @return the list
	 * @throws CommonAPIException the common API exception
	 */
	List<BookingDetailDTO> bookingCancel(String bookingIds) throws CommonAPIException;
	
	/**
	 * Gets the booking.
	 *
	 * @param id         the id
	 * @param city       the city
	 * @param email      the email
	 * @param movieName  the movie name
	 * @param theatre    the theatre
	 * @param customerId the customer id
	 * @param mobile     the mobile
	 * @return the booking
	 * @throws CommonAPIException the common API exception
	 */
	List<BookingDetailDTO> getBooking(Long id, String city, String email, String movieName, String theatre, Long customerId, String mobile) throws CommonAPIException;
}
