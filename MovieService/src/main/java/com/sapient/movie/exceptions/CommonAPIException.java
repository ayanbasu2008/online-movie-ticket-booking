/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.exceptions;

import com.sapient.movie.consts.Exceptions;

/**
 * This action will be thrown whenever action not supported .
 *
 * @author Sandeep Kumar
 */
public class CommonAPIException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The exception field. */
	private String exceptionField;
	
	/** This property will take Exceptions enumerations. */
	private final Exceptions exceptionDetail;
	
	/**
	 * Instantiates a new action not supported exception.
	 *
	 * @param ex the ex
	 */
	public CommonAPIException(Exceptions ex) {
		super(ex.getDefaultMessage());
		this.exceptionDetail = ex;
	}

	/**
	 * Instantiates a new action not supported exception.
	 *
	 * @param ex the ex
	 * @param cause the cause
	 */
	public CommonAPIException(Exceptions ex, Throwable cause) {
		super(ex.getCode(), cause);
		this.exceptionDetail = ex;
	}

	/**
	 * Instantiates a new action not supported exception.
	 *
	 * @param ex the ex
	 * @param exceptionField the exception field
	 */
	public CommonAPIException(Exceptions ex, String exceptionField) {
		super(ex.getDefaultMessage());
		this.exceptionDetail = ex;
		this.exceptionField = exceptionField;
	}

	/**
	 * Instantiates a new action not supported exception.
	 *
	 * @param ex the ex
	 * @param cause the cause
	 * @param exceptionField the exception field
	 */
	public CommonAPIException(Exceptions ex, Throwable cause, String exceptionField) {
		super(ex.getCode(), cause);
		this.exceptionDetail = ex;
		this.exceptionField = exceptionField;
	}
	
	/**
	 * Gets the exception detail.
	 *
	 * @return the exception detail
	 */
	public Exceptions getExceptionDetail() {
		return exceptionDetail;
	}

	/**
	 * Gets the exception field.
	 *
	 * @return the exception field
	 */
	public String getExceptionField() {
		return exceptionField;
	}

	/**
	 * Sets the exception field.
	 *
	 * @param exceptionField the new exception field
	 */
	public void setExceptionField(String exceptionField) {
		this.exceptionField = exceptionField;
	}
	
}