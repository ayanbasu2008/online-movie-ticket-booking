/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * The Class Seat.
 * @author Sandeep Kumar
 */
@Entity
@Table(name = "seat")
public class Seat implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The code. */
	@Column(name = "code")
	@NotNull
	private String code;
	
	/** The show. */
	@ManyToOne
	private Show show;

	/** The booked. */
	@Column(name = "isbooked")
	@NotNull
	private Boolean booked;
	
	/** The booking id. */
	@Column(name = "boolingid")
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
	public Show getShow() {
		return show;
	}

	/**
	 * Sets the show.
	 *
	 * @param show the new show
	 */
	public void setShow(Show show) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((show == null) ? 0 : show.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (show == null) {
			if (other.show != null)
				return false;
		} else if (!show.equals(other.show))
			return false;
		return true;
	}


}
