/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.consts;

import org.springframework.http.HttpStatus;

/**
 * This enumeration contains the list of exceptions.
 *
 * @author Sandeep Kumar
 */
public enum Exceptions {
	
	/** The e0000. */
	E0000(HttpStatus.CONFLICT, "0000", "exception.0000", "Generic exeption - unknown"),
	
	/** The e0001. */
	E0001(HttpStatus.CONFLICT, "0001", "exception.0001", "Illegal State Exception occurred"),
	
	/** The e0002. */
	E0002(HttpStatus.BAD_REQUEST, "0002", "exception.0002", "{0} parameter is missing"),
	
	/** The e0003. */
	E0003(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "0003", "exception.0003", "{0} media type is not supported. Supported media types are "),
	
	/** The e0004. */
	E0004(HttpStatus.BAD_REQUEST, "0004", "exception.0004", "Validation failed"),
	
	/** The e0005. */
	E0005(HttpStatus.BAD_REQUEST, "0005", "exception.0005", "Constraint violation occurred"),
	
	/** The e0006. */
	E0006(HttpStatus.INTERNAL_SERVER_ERROR, "0006", "exception.0006", "Data access exception"),
	
	/** The e0007. */
	E0007(HttpStatus.INTERNAL_SERVER_ERROR, "0007", "exception.0007", "Persistence exception"),
	
	/** The e0008. */
	E0008(HttpStatus.INTERNAL_SERVER_ERROR, "0008", "exception.0008", "SQL grammer exception"),
	
	/** The e0009. */
	E0009(HttpStatus.BAD_REQUEST, "0009", "exception.0009", "Http message not readable exception"),
	
	/** The e0010. */
	E0010(HttpStatus.INTERNAL_SERVER_ERROR, "0010", "exception.0010", "Http message not writeable exception"),
	
	/** The e0011. */
	E0011(HttpStatus.NOT_FOUND, "0011", "exception.0011", "Record not found"),
	
	/** The e0012. */
	E0012(HttpStatus.CONFLICT, "0012", "exception.0012", "Property value exception"),
	
	/** The e0013. */
	E0013(HttpStatus.INTERNAL_SERVER_ERROR, "0013", "exception.0013", "Data integrity violation exception"),
	
	/** The e0014. */
	E0014(HttpStatus.BAD_REQUEST, "0014", "exception.0014", "Method argument type mismatch exception"),
	
	/** The e0015. */
	E0015(HttpStatus.NOT_FOUND, "0015", "exception.0015", "No handler found exception"),
	
	/** The e0016. */
	E0016(HttpStatus.METHOD_NOT_ALLOWED, "0016", "exception.0016", "{0} method is not supported for this request. Supported methods are "),
	
	/** The e0017. */
	E0017(HttpStatus.BAD_REQUEST, "0017", "exception.0017", "Number format exception"),
	
	/** The e0018. */
	E0018(HttpStatus.BAD_REQUEST, "0018", "exception.0018", "Parse exception"),
	
	/** The e0019. */
	E0019(HttpStatus.BAD_REQUEST, "0019", "exception.0019", "Illegal argument exception"),
	
	/** The e0020. */
	E0020(HttpStatus.NOT_FOUND, "0020", "exception.0020", "Resource not found exception"),
	
	/** The e0021. */
	E0021(HttpStatus.INTERNAL_SERVER_ERROR, "0021", "exception.0021", "Global Runtime Exception"),
	
	/** The e0022. */
	E0022(HttpStatus.INTERNAL_SERVER_ERROR, "0022", "exception.0022", "Global Null pointer exception"),
	
	/** The e0023. */
	E0023(HttpStatus.INTERNAL_SERVER_ERROR, "0023", "exception.0023", "Global exception"),
	
	/** The e0024. */
	E0024(HttpStatus.BAD_REQUEST, "0024", "exception.0024", "Empty Request"),
	
	/** The e0025. */
	E0025(HttpStatus.BAD_REQUEST, "0025", "exception.0025", "Invalid input Request"),
	
	/** The e0026. */
	E0026(HttpStatus.BAD_REQUEST, "0026", "exception.0026", "Theatre is already available"),
	
	/** The e0027. */
	E0027(HttpStatus.BAD_REQUEST, "0027", "exception.0027", "Show is already available"),
	
	/** The e0028. */
	E0028(HttpStatus.BAD_REQUEST, "0028", "exception.0028", "Customer is already available"),
	
	/** The e0029. */
	E0029(HttpStatus.BAD_REQUEST, "0029", "exception.0029", "Movie is already available"),
	
	/** The e0030. */
	E0030(HttpStatus.BAD_REQUEST, "0030", "exception.0030", "Booking not available"),
	
	/** The e0031. */
	E0031(HttpStatus.BAD_REQUEST, "0031", "exception.0031", "Email ID and Mobile number exists with two records"),
	
	/** The e0032. */
	E0032(HttpStatus.BAD_REQUEST, "0032", "exception.0032", "City not available"),
	
	/** The e0033. */
	E0033(HttpStatus.BAD_REQUEST, "0033", "exception.0033", "Customer not available"),
	
	/** The e0034. */
	E0034(HttpStatus.BAD_REQUEST, "0034", "exception.0034", "Email id already mapped with other user"),
	
	/** The e0035. */
	E0035(HttpStatus.BAD_REQUEST, "0035", "exception.0035", "Mobile No already mapped with other user"),
	
	/** The e0036. */
	E0036(HttpStatus.BAD_REQUEST, "0036", "exception.0036", "Customer has future bookings, cannot be deleted"),
	
	/** The e0037. */
	E0037(HttpStatus.BAD_REQUEST, "0037", "exception.0037", "Movie not available"),
	
	/** The e0038. */
	E0038(HttpStatus.BAD_REQUEST, "0038", "exception.0038", "Movie mapped with show, therefore cannot be deleted"),
	
	/** The e0039. */
	E0039(HttpStatus.BAD_REQUEST, "0039", "exception.0039", "Show alredy there with given timeframe"),
	
	/** The e0040. */
	E0040(HttpStatus.BAD_REQUEST, "0040", "exception.0040", "Theatre not available"),
	
	/** The e0041. */
	E0041(HttpStatus.BAD_REQUEST, "0041", "exception.0041", "Show alredy available with given show number"),
	
	/** The e0042. */
	E0042(HttpStatus.BAD_REQUEST, "0042", "exception.0042", "Show has bookings so it cannot be deleted. Cancel booking then try delete"),
	
	/** The e0043. */
	E0043(HttpStatus.BAD_REQUEST, "0043", "exception.0043", "There is no show by given id"),
	
	/** The e0044. */
	E0044(HttpStatus.BAD_REQUEST, "0044", "exception.0044", "Seat is already booked so cannot remove"),
	
	/** The e0045. */
	E0045(HttpStatus.BAD_REQUEST, "0045", "exception.0045", "Seat is already avaialble"),
	
	/** The e0046. */
	E0046(HttpStatus.BAD_REQUEST, "0046", "exception.0046", "Provided genres are not available"),
	
	/** The e0047. */
	E0047(HttpStatus.BAD_REQUEST, "0047", "exception.0047", "Provided languages not avaialble"),
	
	/** The e0048. */
	E0048(HttpStatus.BAD_REQUEST, "0048", "exception.0048", "Provided correct booking ids"),
	
	/** The e0049. */
	E0049(HttpStatus.BAD_REQUEST, "0049", "exception.0049", "Given booking id not available"),
	
	/** The e0050. */
	E0050(HttpStatus.BAD_REQUEST, "0050", "exception.0050", "Given booking id already cancelled"),
	
	/** The e0051. */
	E0051(HttpStatus.BAD_REQUEST, "0051", "exception.0051", "Given customer id already cancelled")
	;

	/** The status. */
	private HttpStatus status;
	
	/** The code. */
	private String code;
	
	/** The messagekey. */
	private String messagekey;
	
	/** The default message. */
	private String defaultMessage;
	
	/**
	 * Instantiates a new exceptions.
	 *
	 * @param status         the status
	 * @param code           the code
	 * @param messagekey     the messagekey
	 * @param defaultMessage the default message
	 */
	private Exceptions(HttpStatus status, String code, String messagekey, String defaultMessage) {
		this.status = status;
		this.code = code;
		this.messagekey = messagekey;
		this.defaultMessage = defaultMessage;
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
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the messagekey.
	 *
	 * @return the messagekey
	 */
	public String getMessagekey() {
		return messagekey;
	}

	/**
	 * Gets the default message.
	 *
	 * @return the default message
	 */
	public String getDefaultMessage() {
		return defaultMessage;
	}
}