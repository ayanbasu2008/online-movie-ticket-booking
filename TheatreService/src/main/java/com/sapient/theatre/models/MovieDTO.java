/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.models;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * The Class MovieDTO.
 * @author Sandeep Kumar
 */
public class MovieDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	private Long id;
	
	/** The name. */
	@NotNull
	private String name;
	
	/** The year. */
	@NotNull
	private Integer year;
	
	/** The description. */
	@NotNull
	private String description;
	
	/** The genres. */
	@NotNull
    private Set<String> genres;
	
	/** The languages. */
	@NotNull
    private Set<String> languages;
    
    /** The active. */
    private Boolean active;
    
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
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	
	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the genres.
	 *
	 * @return the genres
	 */
	public Set<String> getGenres() {
		return genres;
	}
	
	/**
	 * Sets the genres.
	 *
	 * @param genres the new genres
	 */
	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}
	
	/**
	 * Gets the languages.
	 *
	 * @return the languages
	 */
	public Set<String> getLanguages() {
		return languages;
	}
	
	/**
	 * Sets the languages.
	 *
	 * @param languages the new languages
	 */
	public void setLanguages(Set<String> languages) {
		this.languages = languages;
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

    
}
