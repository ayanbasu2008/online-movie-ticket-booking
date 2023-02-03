/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.models;

import java.io.Serializable;
import java.util.Set;

/**
 * The Class TicketDTO.
 * @author Sandeep Kumar
 */
public class TicketDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The theatre. */
	private TheatreDTO theatre;
	
	/** The show. */
	private ShowDTO show;
	
	/** The movie. */
	private MovieDTO movie;
	
	/** The offers. */
	private Set<OfferDTO> offers;
	
	/** The seats. */
	private ShowSeatsDTO seats;
	
	/**
	 * Gets the theatre.
	 *
	 * @return the theatre
	 */
	public TheatreDTO getTheatre() {
		return theatre;
	}
	
	/**
	 * Sets the theatre.
	 *
	 * @param theatre the new theatre
	 */
	public void setTheatre(TheatreDTO theatre) {
		this.theatre = theatre;
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
	 * Gets the movie.
	 *
	 * @return the movie
	 */
	public MovieDTO getMovie() {
		return movie;
	}
	
	/**
	 * Sets the movie.
	 *
	 * @param movie the new movie
	 */
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	
	/**
	 * Gets the seats.
	 *
	 * @return the seats
	 */
	public ShowSeatsDTO getSeats() {
		return seats;
	}
	
	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(ShowSeatsDTO seats) {
		this.seats = seats;
	}
	
	/**
	 * Gets the offers.
	 *
	 * @return the offers
	 */
	public Set<OfferDTO> getOffers() {
		return offers;
	}
	
	/**
	 * Sets the offers.
	 *
	 * @param offers the new offers
	 */
	public void setOffers(Set<OfferDTO> offers) {
		this.offers = offers;
	}
	
	

}
