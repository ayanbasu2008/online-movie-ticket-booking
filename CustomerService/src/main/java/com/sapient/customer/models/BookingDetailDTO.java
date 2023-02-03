/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The Class BookingDetailDTO.
 * @author Sandeep Kumar
 */
public class BookingDetailDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
			
	/** The id. */
	private Long id;
	
	/** The base price. */
	private Float basePrice;
	
	/** The discount. */
	private Float discount;
	
	/** The final price. */
	private Float finalPrice;
	
	/** The tax. */
	private Float tax;
	
	/** The is cancelled. */
	private Boolean isCancelled;
	
	/** The local date time. */
	private LocalDateTime localDateTime;
	
	/** The status. */
	private String status;
	
	/** The customer. */
	private CustomerDTO customer;
	
	/** The theatre. */
	private TheatreDTO theatre;
	
	/** The show. */
	private ShowDTO show;
	
	/** The movie. */
	private MovieDTO movie;
	
	/** The seats. */
	private Set<SeatDTO> seats;
	
	/** The booking request. */
	private BookingRequestDTO bookingRequest;
	
	/** The message. */
	private String message;
	
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
	 * Gets the base price.
	 *
	 * @return the base price
	 */
	public Float getBasePrice() {
		return basePrice;
	}
	
	/**
	 * Sets the base price.
	 *
	 * @param basePrice the new base price
	 */
	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}
	
	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public Float getDiscount() {
		return discount;
	}
	
	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	/**
	 * Gets the final price.
	 *
	 * @return the final price
	 */
	public Float getFinalPrice() {
		return finalPrice;
	}
	
	/**
	 * Sets the final price.
	 *
	 * @param finalPrice the new final price
	 */
	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	/**
	 * Gets the checks if is cancelled.
	 *
	 * @return the checks if is cancelled
	 */
	public Boolean getIsCancelled() {
		return isCancelled;
	}
	
	/**
	 * Sets the checks if is cancelled.
	 *
	 * @param isCancelled the new checks if is cancelled
	 */
	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	/**
	 * Gets the local date time.
	 *
	 * @return the local date time
	 */
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	/**
	 * Sets the local date time.
	 *
	 * @param localDateTime the new local date time
	 */
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}
	
	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
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
	public Set<SeatDTO> getSeats() {
		return seats;
	}
	
	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(Set<SeatDTO> seats) {
		this.seats = seats;
	}
	
	/**
	 * Gets the booking request.
	 *
	 * @return the booking request
	 */
	public BookingRequestDTO getBookingRequest() {
		return bookingRequest;
	}
	
	/**
	 * Sets the booking request.
	 *
	 * @param bookingRequest the new booking request
	 */
	public void setBookingRequest(BookingRequestDTO bookingRequest) {
		this.bookingRequest = bookingRequest;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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

	

}
