/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * The Class ResponseDTO.
 * @author Sandeep Kumar
 *
 * @param <T> the generic type
 */
public class ResponseDTO<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data. */
	private T data;
	
	/** The message. */
	private String message;
	
	/** The status. */
	private HttpStatus status;
	
	/** The response time. */
	private Date responseTime;
	
	/**
	 * Instantiates a new response DTO.
	 *
	 * @param data    the data
	 * @param message the message
	 * @param status  the status
	 */
	public ResponseDTO(T data, String message, HttpStatus status) {
		super();
		this.data = data;
		this.message = message;
		this.status = status;
		responseTime = new Date();
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data) {
		this.data = data;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * Gets the response time.
	 *
	 * @return the response time
	 */
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * Sets the response time.
	 *
	 * @param responseTime the new response time
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	
}