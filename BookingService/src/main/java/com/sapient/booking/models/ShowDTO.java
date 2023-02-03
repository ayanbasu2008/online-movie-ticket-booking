/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class ShowDTO.
 * @author Sandeep Kumar
 */
public class ShowDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;

	/** The id. */
	private Long id;
	
	/** The show no. */
	@NotNull
	private String showNo;
	
	/** The local date. */
	@NotNull
	private LocalDate localDate;
	
	/** The from time. */
	@NotNull
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime fromTime;
	
	/** The to time. */
	@NotNull
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime toTime;
	
	/** The seats. */
	private ShowSeatsDTO seats;
    
    /** The movie. */
    private MovieDTO movie;
	
	/** The price. */
	@NotNull
	private Float price;
	
	/** The tax. */
	@NotNull
	private Float tax;
	
	/** The screen. */
	@NotNull
	private String screen;
	
	/** The movie id. */
	@NotNull
	private Long movieId;
	
	/** The theatre id. */
	@NotNull
	private Long theatreId;
	
	/** The after noon. */
	@NotNull
	private Boolean afterNoon;
	
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
	 * Gets the show no.
	 *
	 * @return the show no
	 */
	public String getShowNo() {
		return showNo;
	}
	
	/**
	 * Sets the show no.
	 *
	 * @param showNo the new show no
	 */
	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}
	
	/**
	 * Gets the local date.
	 *
	 * @return the local date
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}
	
	/**
	 * Sets the local date.
	 *
	 * @param localDate the new local date
	 */
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	/**
	 * Gets the from time.
	 *
	 * @return the from time
	 */
	public LocalTime getFromTime() {
		return fromTime;
	}
	
	/**
	 * Sets the from time.
	 *
	 * @param fromTime the new from time
	 */
	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}
	
	/**
	 * Gets the to time.
	 *
	 * @return the to time
	 */
	public LocalTime getToTime() {
		return toTime;
	}
	
	/**
	 * Sets the to time.
	 *
	 * @param toTime the new to time
	 */
	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
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
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	
	/**
	 * Gets the tax.
	 *
	 * @return the tax
	 */
	public Float getTax() {
		return tax;
	}
	
	/**
	 * Sets the tax.
	 *
	 * @param tax the new tax
	 */
	public void setTax(Float tax) {
		this.tax = tax;
	}
	
	/**
	 * Gets the movie id.
	 *
	 * @return the movie id
	 */
	public Long getMovieId() {
		return movieId;
	}
	
	/**
	 * Sets the movie id.
	 *
	 * @param movieId the new movie id
	 */
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
	/**
	 * Gets the theatre id.
	 *
	 * @return the theatre id
	 */
	public Long getTheatreId() {
		return theatreId;
	}
	
	/**
	 * Sets the theatre id.
	 *
	 * @param theatreId the new theatre id
	 */
	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}
	
	/**
	 * Gets the screen.
	 *
	 * @return the screen
	 */
	public String getScreen() {
		return screen;
	}
	
	/**
	 * Sets the screen.
	 *
	 * @param screen the new screen
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}
	
	/**
	 * Gets the after noon.
	 *
	 * @return the after noon
	 */
	public Boolean getAfterNoon() {
		return afterNoon;
	}
	
	/**
	 * Sets the after noon.
	 *
	 * @param afterNoon the new after noon
	 */
	public void setAfterNoon(Boolean afterNoon) {
		this.afterNoon = afterNoon;
	}
	
	
}
