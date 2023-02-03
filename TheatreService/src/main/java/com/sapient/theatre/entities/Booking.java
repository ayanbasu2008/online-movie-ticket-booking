/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Booking.
 * @author Sandeep Kumar
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The customer. */
	@ManyToOne
	private Customer customer;

	/** The base price. */
	@Column(name = "baseprice")
	private Float basePrice;
	
	/** The discount. */
	@Column(name = "discount")
	private Float discount;
	
	/** The tax. */
	@Column(name = "tax")
	private Float tax;

	/** The final price. */
	@Column(name = "finalPrice")
	private Float finalPrice;
	
	/** The is cancelled. */
	@Column(name = "iscancelled")
	private Boolean isCancelled;

	/** The local date time. */
	@Column(name = "bookingtime")
	private LocalDateTime localDateTime;
	
	/** The theatre. */
	@Column(name = "theatre")
	private Long theatre;
	
	/** The show. */
	@Column(name = "show")
	private Long show;
	
	/** The seats. */
	@Column(name = "seats")
	@ElementCollection
	private List<Long> seats;

	/** The movie. */
	@Column(name = "movie")
	private Long movie;

	/** The status. */
	@Column(name = "status")
	private String status;
	
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
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	 * Gets the theatre.
	 *
	 * @return the theatre
	 */
	public Long getTheatre() {
		return theatre;
	}

	/**
	 * Sets the theatre.
	 *
	 * @param theatre the new theatre
	 */
	public void setTheatre(Long theatre) {
		this.theatre = theatre;
	}

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
	 * Gets the seats.
	 *
	 * @return the seats
	 */
	public List<Long> getSeats() {
		return seats;
	}

	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(List<Long> seats) {
		this.seats = seats;
	}

	/**
	 * Gets the movie.
	 *
	 * @return the movie
	 */
	public Long getMovie() {
		return movie;
	}

	/**
	 * Sets the movie.
	 *
	 * @param movie the new movie
	 */
	public void setMovie(Long movie) {
		this.movie = movie;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((localDateTime == null) ? 0 : localDateTime.hashCode());
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((seats == null) ? 0 : seats.hashCode());
		result = prime * result + ((show == null) ? 0 : show.hashCode());
		result = prime * result + ((theatre == null) ? 0 : theatre.hashCode());
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
		Booking other = (Booking) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!localDateTime.equals(other.localDateTime))
			return false;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (seats == null) {
			if (other.seats != null)
				return false;
		} else if (!seats.equals(other.seats))
			return false;
		if (show == null) {
			if (other.show != null)
				return false;
		} else if (!show.equals(other.show))
			return false;
		if (theatre == null) {
			if (other.theatre != null)
				return false;
		} else if (!theatre.equals(other.theatre))
			return false;
		return true;
	}

	
	
}
