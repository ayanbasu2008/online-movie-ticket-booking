/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.booking.consts.CommonMessageConstants;
import com.sapient.booking.consts.MiscConstants;
import com.sapient.booking.consts.UrlConstants;
import com.sapient.booking.exceptions.CommonAPIException;
import com.sapient.booking.models.BookingRequestDTO;
import com.sapient.booking.services.BookingService;
import com.sapient.booking.utils.AppWebUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * The Class BookingController.
 * @author Sandeep Kumar
 */
@Api(tags = {MiscConstants.SG_BOOKING})
@SwaggerDefinition(tags = {@Tag(name = MiscConstants.SG_BOOKING, description = "This API provides endpoints for booking")})
@RestController
@RequestMapping(UrlConstants.BOOKING_API_BASE + UrlConstants.V1 + UrlConstants.API)
public class BookingController {
	
	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(BookingController.class);

	/** The booking service. */
	@Autowired
	private BookingService bookingService;

	/**
	 * Gets the by theatre.
	 *
	 * @param name  the name
	 * @param city  the city
	 * @param movie the movie
	 * @param date  the date
	 * @return the by theatre
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Get Theatre, Show, Movie and Offer by Name or City or Movie or Show date", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Detail retrieved successfully", response = ResponseEntity.class)
			} )	
	@GetMapping(UrlConstants.BOOKING_API_THEATRE_BY_NAME_CITY_MOVIE)
	ResponseEntity<?> getByTheatre(@RequestParam(name = MiscConstants.NAME, required = false) String name, @RequestParam(name = MiscConstants.CITY, required = false) String city, @RequestParam(name = MiscConstants.MOVIE, required = false) String movie, @RequestParam(name = MiscConstants.S_DATE, required = false) String date) {
		logger.info("getByTheatre:Fetch started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, bookingService.getByDetails(name, city, movie, date));
	}

	/**
	 * Booking.
	 *
	 * @param bookingRequests the booking requests
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.POST, value = "Create ticket booking", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Booking successfully completed", response = ResponseEntity.class)
			} )	
	@PostMapping
	ResponseEntity<?> booking(@RequestBody List<BookingRequestDTO> bookingRequests) throws CommonAPIException {
		logger.info("booking:Booking creation started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, bookingService.booking(bookingRequests));
	}
	
	/**
	 * Booking cancel.
	 *
	 * @param bookingIds the booking ids
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.DELETE, value = "Cancel ticket booking", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Booking cancelled successfully", response = ResponseEntity.class)
			} )	
	@DeleteMapping
	ResponseEntity<?> bookingCancel(@RequestParam(name = MiscConstants.BOOKING_IDS, required = true) String bookingIds) throws CommonAPIException {
		logger.info("bookingCancel:Booking cancellation started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.ACCEPTED, bookingService.bookingCancel(bookingIds));
	}

	/**
	 * Gets the booking.
	 *
	 * @param id         the id
	 * @param city       the city
	 * @param email      the email
	 * @param movieName  the movie name
	 * @param theatre    the theatre
	 * @param customerId the customer id
	 * @param mobile     the mobile
	 * @return the booking
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Obtain booking by id or city or customer name or movie name or theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Booking retrieved successfully", response = ResponseEntity.class)
			} )	
	@GetMapping
	ResponseEntity<?> getBooking(@RequestParam(name = MiscConstants.ID, required = false) Long id, @RequestParam(name = MiscConstants.CITY, required = false) String city, @RequestParam(name = MiscConstants.EMAIL, required = false) String email, @RequestParam(name = MiscConstants.MOVIE, required = false) String movieName, @RequestParam(name = MiscConstants.THEATRE, required = false) String theatre, @RequestParam(name = MiscConstants.CUSTOMER_ID, required = false) Long customerId, @RequestParam(name = MiscConstants.MOBILE, required = false) String mobile) throws CommonAPIException {
		logger.info("getBooking:Booking fetch started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, bookingService.getBooking(id, city, email, movieName, theatre, customerId, mobile));
	}
}