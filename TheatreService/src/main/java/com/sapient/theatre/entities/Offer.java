/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * The Class Offer.
 * @author Sandeep Kumar
 */
@Entity
@Table(name = "offer")
public class Offer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The discount type. */
	@Column(name = "discountType")
	@NotNull
	private Integer discountType;
	
	/** The discount type desc. */
	@Column(name = "discountTypeDesc")
	@NotNull
	private String discountTypeDesc;

	/** The ticket count. */
	@Column(name = "ticketCount")
	@NotNull
	private Integer ticketCount;

	/** The percent discount. */
	@Column(name = "percentDiscount")
	@NotNull
	private Float percentDiscount;
	
	/** The theatres. */
	@ManyToMany
	private List<Theatre> theatres;

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
	 * Gets the ticket count.
	 *
	 * @return the ticket count
	 */
	public Integer getTicketCount() {
		return ticketCount;
	}

	/**
	 * Sets the ticket count.
	 *
	 * @param ticketCount the new ticket count
	 */
	public void setTicketCount(Integer ticketCount) {
		this.ticketCount = ticketCount;
	}

	/**
	 * Gets the percent discount.
	 *
	 * @return the percent discount
	 */
	public Float getPercentDiscount() {
		return percentDiscount;
	}

	/**
	 * Sets the percent discount.
	 *
	 * @param percentDiscount the new percent discount
	 */
	public void setPercentDiscount(Float percentDiscount) {
		this.percentDiscount = percentDiscount;
	}
	
	/**
	 * Gets the discount type.
	 *
	 * @return the discount type
	 */
	public Integer getDiscountType() {
		return discountType;
	}

	/**
	 * Sets the discount type.
	 *
	 * @param discountType the new discount type
	 */
	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	/**
	 * Gets the discount type desc.
	 *
	 * @return the discount type desc
	 */
	public String getDiscountTypeDesc() {
		return discountTypeDesc;
	}

	/**
	 * Sets the discount type desc.
	 *
	 * @param discountTypeDesc the new discount type desc
	 */
	public void setDiscountTypeDesc(String discountTypeDesc) {
		this.discountTypeDesc = discountTypeDesc;
	}

	/**
	 * Gets the theatres.
	 *
	 * @return the theatres
	 */
	public List<Theatre> getTheatres() {
		return theatres;
	}

	/**
	 * Sets the theatres.
	 *
	 * @param theatres the new theatres
	 */
	public void setTheatres(List<Theatre> theatres) {
		this.theatres = theatres;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticketCount == null) ? 0 : ticketCount.hashCode());
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
		Offer other = (Offer) obj;
		if (ticketCount == null) {
			if (other.ticketCount != null)
				return false;
		} else if (!ticketCount.equals(other.ticketCount))
			return false;
		return true;
	}

}
