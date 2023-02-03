/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.models;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The Class TheatreDTO.
 * @author Sandeep Kumar
 */
public class TheatreDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;

	/** The id. */
	private Long id;
	
	/** The name. */
	@NotNull
	private String name;
	
	/** The rows. */
	@NotNull
	@Min(value = 1)
	private Integer rows;
	
	/** The columns. */
	@NotNull
	@Min(value = 1)
	private Integer columns;
    
    /** The address. */
    private String address;
    
    /** The city. */
    @NotNull
    private String city;
	
	/** The active. */
	private Boolean active;
    
    /** The shows. */
    private Set<ShowDTO> shows;
    
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}
	
	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public Integer getColumns() {
		return columns;
	}
	
	/**
	 * Sets the columns.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**
	 * Gets the shows.
	 *
	 * @return the shows
	 */
	public Set<ShowDTO> getShows() {
		return shows;
	}
	
	/**
	 * Sets the shows.
	 *
	 * @param shows the new shows
	 */
	public void setShows(Set<ShowDTO> shows) {
		this.shows = shows;
	}
    
    
}
