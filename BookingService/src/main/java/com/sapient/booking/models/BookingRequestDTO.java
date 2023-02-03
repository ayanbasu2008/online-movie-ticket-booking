/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.models;

import java.io.Serializable;
import java.util.List;

/**
 * The Class BookingRequestDTO.
 * @author Sandeep Kumar
 */
public class BookingRequestDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The customer. */
	private Long customer;
	
	/** The show. */
	private Long show;
	
	/** The seats. */
	private List<String> seats;
	
	/**
	 * Gets the show.
	 *
	 * @return the show
	 */
	public Long getShow() {
		return show;
	}
	
	/**
	 * Sets the show.
	 *
	 * @param show the new show
	 */
	public void setShow(Long show) {
		this.show = show;
	}
	
	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Long getCustomer() {
		return customer;
	}
	
	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Long customer) {
		this.customer = customer;
	}
	
	/**
	 * Gets the seats.
	 *
	 * @return the seats
	 */
	public List<String> getSeats() {
		return seats;
	}
	
	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

}
