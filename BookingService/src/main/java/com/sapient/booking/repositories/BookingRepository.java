/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.repositories;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.booking.entities.Booking;

/**
 * The Interface BookingRepository.
 * @author Sandeep Kumar
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	/**
	 * Gets the booking by customer.
	 *
	 * @param id the id
	 * @return the booking by customer
	 */
	@Query("select case when count(b) > 0 then true else false end from Booking b where b.customer.id = ?1")
	boolean getBookingByCustomer(Long id);

	/**
	 * Gets the booking by customer future dated.
	 *
	 * @param id            the id
	 * @param localDateTime the local date time
	 * @return the booking by customer future dated
	 */
	@Query("select case when count(b) > 0 then true else false end from Booking b where b.customer.id = ?1 and b.localDateTime > ?2")
	boolean getBookingByCustomerFutureDated(Long id, LocalDateTime localDateTime);
	
	/**
	 * Gets the booking by show.
	 *
	 * @param id the id
	 * @return the booking by show
	 */
	@Query("select case when count(b) > 0 then true else false end from Booking b where b.show = ?1 and b.status = 'BOOKED'")
	boolean getBookingByShow(Long id);
	
	/**
	 * Gets the bookings by customer.
	 *
	 * @param id the id
	 * @return the bookings by customer
	 */
	@Query("select b from Booking b where b.customer.id = ?1")
	Set<Booking> getBookingsByCustomer(Long id);

	/**
	 * Gets the bookings by customer email.
	 *
	 * @param email the email
	 * @return the bookings by customer email
	 */
	@Query("select b from Booking b where b.customer.email = ?1")
	Set<Booking> getBookingsByCustomerEmail(String email);

	/**
	 * Gets the bookings by customer mobile no.
	 *
	 * @param mobileNo the mobile no
	 * @return the bookings by customer mobile no
	 */
	@Query("select b from Booking b where b.customer.mobileNo = ?1")
	Set<Booking> getBookingsByCustomerMobileNo(String mobileNo);

	/**
	 * Gets the bookings by customer alt mobile no.
	 *
	 * @param altMobileNo the alt mobile no
	 * @return the bookings by customer alt mobile no
	 */
	@Query("select b from Booking b where b.customer.altMobileNo = ?1")
	Set<Booking> getBookingsByCustomerAltMobileNo(String altMobileNo);

	/**
	 * Gets the bookings by customer city.
	 *
	 * @param city the city
	 * @return the bookings by customer city
	 */
	@Query("select b from Booking b where b.customer.city = ?1")
	Set<Booking> getBookingsByCustomerCity(String city);

	/**
	 * Gets the bookings by city theatre movie.
	 *
	 * @param city    the city
	 * @param theatre the theatre
	 * @param movie   the movie
	 * @return the bookings by city theatre movie
	 */
	@Query("select b from Booking b, Show s where b.show = s.id and s.theatre.city = ?1 and s.theatre.name = ?2 and s.movie.name = ?3")
	Set<Booking> getBookingsByCityTheatreMovie(String city, String theatre, String movie);

	/**
	 * Gets the bookings by city theatre.
	 *
	 * @param city    the city
	 * @param theatre the theatre
	 * @return the bookings by city theatre
	 */
	@Query("select b from Booking b, Show s where b.show = s.id and s.theatre.city = ?1 and s.theatre.name = ?2")
	Set<Booking> getBookingsByCityTheatre(String city, String theatre);

	/**
	 * Gets the bookings by theatre movie.
	 *
	 * @param theatre the theatre
	 * @param movie   the movie
	 * @return the bookings by theatre movie
	 */
	@Query("select b from Booking b, Show s where b.show = s.id and s.theatre.name = ?1 and s.movie.name = ?2")
	Set<Booking> getBookingsByTheatreMovie(String theatre, String movie);
}
