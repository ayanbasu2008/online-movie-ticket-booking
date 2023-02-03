/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.models;

import java.io.Serializable;

/**
 * The Class OfferDTO.
 * @author Sandeep Kumar
 */
public class OfferDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	private Long id;
	
	/** The discount type. */
	private Integer discountType;
	
	/** The discount type desc. */
	private String discountTypeDesc;
	
	/** The ticket count. */
	private Integer ticketCount;
	
	/** The percent discount. */
	private Float percentDiscount;
	
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
}
