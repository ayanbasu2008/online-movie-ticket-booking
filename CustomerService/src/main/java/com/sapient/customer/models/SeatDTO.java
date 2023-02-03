/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * The Class SeatDTO.
 */
public class SeatDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private Long id;
	
	/** The code. */
	@NotNull
	private String code;
	
	/** The show. */
	private ShowDTO show;
	
	/** The booked. */
	private Boolean booked;
	
	/** The booking id. */
	private Long bookingId;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the show.
	 *
	 * @return the show
	 */
	public ShowDTO getShow() {
		return show;
	}
	
	/**
	 * Sets the show.
	 *
	 * @param show the new show
	 */
	public void setShow(ShowDTO show) {
		this.show = show;
	}
	
	/**
	 * Gets the booked.
	 *
	 * @return the booked
	 */
	public Boolean getBooked() {
		return booked;
	}
	
	/**
	 * Sets the booked.
	 *
	 * @param booked the new booked
	 */
	public void setBooked(Boolean booked) {
		this.booked = booked;
	}
	
	/**
	 * Gets the booking id.
	 *
	 * @return the booking id
	 */
	public Long getBookingId() {
		return bookingId;
	}
	
	/**
	 * Sets the booking id.
	 *
	 * @param bookingId the new booking id
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	
	

}
