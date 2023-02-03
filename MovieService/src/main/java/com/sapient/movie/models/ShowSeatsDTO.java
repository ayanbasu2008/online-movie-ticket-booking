/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.models;

import java.io.Serializable;
import java.util.List;

/**
 * The Class ShowSeatsDTO.
 * @author Sandeep Kumar
 */
public class ShowSeatsDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The total seats. */
	private List<SeatDTO> totalSeats;
	
	/** The booked seats. */
	private List<SeatDTO> bookedSeats;
	
	/** The free seats. */
	private List<SeatDTO> freeSeats;
	
	/** The total seat count. */
	private Integer totalSeatCount;
	
	/** The booked seat count. */
	private Integer bookedSeatCount;
	
	/** The free seat count. */
	private Integer freeSeatCount;

	/** The show id. */
	private Long showId;
	
	/** The to be added. */
	private List<SeatDTO> toBeAdded;
	
	/** The to be removed. */
	private List<SeatDTO> toBeRemoved;
	
	/**
	 * Gets the total seats.
	 *
	 * @return the total seats
	 */
	public List<SeatDTO> getTotalSeats() {
		return totalSeats;
	}
	
	/**
	 * Sets the total seats.
	 *
	 * @param totalSeats the new total seats
	 */
	public void setTotalSeats(List<SeatDTO> totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	/**
	 * Gets the booked seats.
	 *
	 * @return the booked seats
	 */
	public List<SeatDTO> getBookedSeats() {
		return bookedSeats;
	}
	
	/**
	 * Sets the booked seats.
	 *
	 * @param bookedSeats the new booked seats
	 */
	public void setBookedSeats(List<SeatDTO> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	
	/**
	 * Gets the free seats.
	 *
	 * @return the free seats
	 */
	public List<SeatDTO> getFreeSeats() {
		return freeSeats;
	}
	
	/**
	 * Sets the free seats.
	 *
	 * @param freeSeats the new free seats
	 */
	public void setFreeSeats(List<SeatDTO> freeSeats) {
		this.freeSeats = freeSeats;
	}
	
	/**
	 * Gets the total seat count.
	 *
	 * @return the total seat count
	 */
	public Integer getTotalSeatCount() {
		return totalSeatCount;
	}
	
	/**
	 * Sets the total seat count.
	 *
	 * @param totalSeatCount the new total seat count
	 */
	public void setTotalSeatCount(Integer totalSeatCount) {
		this.totalSeatCount = totalSeatCount;
	}
	
	/**
	 * Gets the booked seat count.
	 *
	 * @return the booked seat count
	 */
	public Integer getBookedSeatCount() {
		return bookedSeatCount;
	}
	
	/**
	 * Sets the booked seat count.
	 *
	 * @param bookedSeatCount the new booked seat count
	 */
	public void setBookedSeatCount(Integer bookedSeatCount) {
		this.bookedSeatCount = bookedSeatCount;
	}
	
	/**
	 * Gets the free seat count.
	 *
	 * @return the free seat count
	 */
	public Integer getFreeSeatCount() {
		return freeSeatCount;
	}
	
	/**
	 * Sets the free seat count.
	 *
	 * @param freeSeatCount the new free seat count
	 */
	public void setFreeSeatCount(Integer freeSeatCount) {
		this.freeSeatCount = freeSeatCount;
	}
	
	/**
	 * Gets the to be added.
	 *
	 * @return the to be added
	 */
	public List<SeatDTO> getToBeAdded() {
		return toBeAdded;
	}
	
	/**
	 * Sets the to be added.
	 *
	 * @param toBeAdded the new to be added
	 */
	public void setToBeAdded(List<SeatDTO> toBeAdded) {
		this.toBeAdded = toBeAdded;
	}
	
	/**
	 * Gets the to be removed.
	 *
	 * @return the to be removed
	 */
	public List<SeatDTO> getToBeRemoved() {
		return toBeRemoved;
	}
	
	/**
	 * Sets the to be removed.
	 *
	 * @param toBeRemoved the new to be removed
	 */
	public void setToBeRemoved(List<SeatDTO> toBeRemoved) {
		this.toBeRemoved = toBeRemoved;
	}
	
	/**
	 * Gets the show id.
	 *
	 * @return the show id
	 */
	public Long getShowId() {
		return showId;
	}
	
	/**
	 * Sets the show id.
	 *
	 * @param showId the new show id
	 */
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	
	
}
