/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * The Class Show.
 * @author Sandeep Kumar
 */
@Entity
@Table(name = "show")
public class Show implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The show no. */
	@Column(name = "showno")
	@NotNull
	private String showNo;

	/** The local date. */
	@Column(name = "showdate")
	@NotNull
	private LocalDate localDate;

	/** The from time. */
	@Column(name = "fromtime")
	@NotNull
	private LocalTime fromTime;

	/** The to time. */
	@Column(name = "totime")
	@NotNull
	private LocalTime toTime;

	/** The seats. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Seat> seats;
	
	/** The movie. */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Movie movie;

	/** The price. */
	@Column(name = "price")
	private Float price;

	/** The tax. */
	@Column(name = "tax")
	private Float tax;
	
	/** The screen. */
	@Column(name = "screen")
	private String screen;
	
	/** The theatre. */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Theatre theatre;
	
	/** The after noon. */
	@Column(name = "isafternoon")
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
	public Set<Seat> getSeats() {
		return seats;
	}

	/**
	 * Sets the seats.
	 *
	 * @param seats the new seats
	 */
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
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
	 * Gets the movie.
	 *
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * Sets the movie.
	 *
	 * @param movie the new movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
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
	 * Gets the theatre.
	 *
	 * @return the theatre
	 */
	public Theatre getTheatre() {
		return theatre;
	}

	/**
	 * Sets the theatre.
	 *
	 * @param theatre the new theatre
	 */
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromTime == null) ? 0 : fromTime.hashCode());
		result = prime * result + ((localDate == null) ? 0 : localDate.hashCode());
		result = prime * result + ((toTime == null) ? 0 : toTime.hashCode());
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
		Show other = (Show) obj;
		if (fromTime == null) {
			if (other.fromTime != null)
				return false;
		} else if (!fromTime.equals(other.fromTime))
			return false;
		if (localDate == null) {
			if (other.localDate != null)
				return false;
		} else if (!localDate.equals(other.localDate))
			return false;
		if (toTime == null) {
			if (other.toTime != null)
				return false;
		} else if (!toTime.equals(other.toTime))
			return false;
		return true;
	}


}