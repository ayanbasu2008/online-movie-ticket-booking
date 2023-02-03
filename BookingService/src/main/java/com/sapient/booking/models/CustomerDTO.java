/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.models;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.sapient.booking.annotations.StringEnumeration;
import com.sapient.booking.consts.Gender;

/**
 * The Class CustomerDTO.
 * @author Sandeep Kumar
 */
public class CustomerDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0l;
	
	/** The id. */
	private Long id;
	
	/** The first name. */
	@NotNull
	private String firstName;
	
	/** The middle name. */
	private String middleName;
	
	/** The last name. */
	@NotNull
	private String lastName;
	
	/** The mobile no. */
	@NotNull
	private String mobileNo;
	
	/** The email. */
	@NotNull
	@Email
	private String email;
	
	/** The alt mobile no. */
	private String altMobileNo;
	
	/** The age. */
	@NotNull
	@Min(value = 13)
	@Max(value = 100)
	private Integer age;
	
	/** The gender. */
	@NotNull
	@StringEnumeration(enumClass = Gender.class)
	private String gender;
	
	/** The address. */
	private String address;
	
	/** The city. */
	private String city;
	
	/** The individual. */
	@NotNull
	private Boolean individual;
	
	/** The active. */
	private Boolean active;
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	
	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the alt mobile no.
	 *
	 * @return the alt mobile no
	 */
	public String getAltMobileNo() {
		return altMobileNo;
	}
	
	/**
	 * Sets the alt mobile no.
	 *
	 * @param altMobileNo the new alt mobile no
	 */
	public void setAltMobileNo(String altMobileNo) {
		this.altMobileNo = altMobileNo;
	}
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * Gets the individual.
	 *
	 * @return the individual
	 */
	public Boolean getIndividual() {
		return individual;
	}
	
	/**
	 * Sets the individual.
	 *
	 * @param individual the new individual
	 */
	public void setIndividual(Boolean individual) {
		this.individual = individual;
	}
	
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
