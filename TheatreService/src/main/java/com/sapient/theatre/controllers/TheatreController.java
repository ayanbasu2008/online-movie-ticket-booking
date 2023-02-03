/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.theatre.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.theatre.consts.CommonMessageConstants;
import com.sapient.theatre.consts.MiscConstants;
import com.sapient.theatre.consts.UrlConstants;
import com.sapient.theatre.exceptions.CommonAPIException;
import com.sapient.theatre.models.ShowDTO;
import com.sapient.theatre.models.ShowSeatsDTO;
import com.sapient.theatre.models.TheatreDTO;
import com.sapient.theatre.services.TheatreService;
import com.sapient.theatre.utils.AppWebUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * The Class TheatreController.
 * @author Sandeep Kumar
 */
@Api(tags = {MiscConstants.SG_THEATRE})
@SwaggerDefinition(tags = {@Tag(name = MiscConstants.SG_THEATRE, description = "This API provides endpoints for Theatre management")})
@RestController
@RequestMapping(UrlConstants.THEATRE_API_BASE + UrlConstants.V1 + UrlConstants.API)
public class 	TheatreController {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(TheatreController.class);
	
	/** The theatre service. */
	@Autowired
	private TheatreService theatreService;

	/**
	 * Creates the.
	 *
	 * @param theatre the theatre
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.POST, value = "Create Theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Theatre created successfully", response = ResponseEntity.class)
			} )	
	@PostMapping
		ResponseEntity<?> create(@RequestBody TheatreDTO theatre) throws CommonAPIException {
		logger.info("create:Theatre creation started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, theatreService.create(theatre));
	}
	
	/**
	 * Update.
	 *
	 * @param theatre the theatre
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.PUT, value = "Update Theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Theatre updated successfully", response = ResponseEntity.class)
			} )	
	@PutMapping
	ResponseEntity<?> update(@RequestBody TheatreDTO theatre) throws CommonAPIException {
		logger.info("update:Theatre update started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_UPDATED, HttpStatus.OK, theatreService.update(theatre));
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.DELETE, value = "Delete Theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 202, message = "Theatre deleted successfully", response = ResponseEntity.class)
			} )	
	@DeleteMapping
	ResponseEntity<?> delete(@RequestParam Long id) throws CommonAPIException {
		logger.info("delete:Theatre delete started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.ACCEPTED, theatreService.delete(id));
	}
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Fetch Theatre by ID", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Theatre fetched successfully", response = ResponseEntity.class)
			} )	
	@GetMapping
	ResponseEntity<?> getById(@RequestParam Long id) throws CommonAPIException {
		logger.info("getById:Theatre fetch by id started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, theatreService.getById(id));
	}
	
	/**
	 * Gets the all.
	 *
	 * @param query    the query
	 * @param pageable the pageable
	 * @return the all
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Fetch Theatre by all criteria", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Theatre fetched successfully", response = ResponseEntity.class)
			} )	
	@GetMapping(UrlConstants.ALL)
	ResponseEntity<?> getAll(@RequestParam(name = MiscConstants.QUERY, required = false) String query, Pageable pageable) {
		logger.info("getAll:Theatre get all started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, theatreService.getAll(query, pageable));
	}

	/**
	 * Creates the show.
	 *
	 * @param show the show
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.POST, value = "Create show in theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Show created successfully", response = ResponseEntity.class)
			} )	
	@PostMapping(UrlConstants.THEATRE_API_CREATE_SHOW)
	ResponseEntity<?> createShow(@RequestBody ShowDTO show) throws CommonAPIException {
		logger.info("createShow:Show creating started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, theatreService.createShow(show));
	}

	/**
	 * Creates the show.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.DELETE, value = "Delete show in theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 202, message = "Show deleted successfully", response = ResponseEntity.class)
			} )	
	@DeleteMapping(UrlConstants.THEATRE_API_DELETE_SHOW)
	ResponseEntity<?> createShow(@RequestParam Long id) throws CommonAPIException {
		logger.info("createShow:Show deletion started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.CREATED, theatreService.deleteShow(id));
	}

	/**
	 * Update show.
	 *
	 * @param show the show
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.POST, value = "Update seats for show in theatre", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Update seats successfully", response = ResponseEntity.class)
			} )	
	@PostMapping(UrlConstants.THEATRE_API_UPDATE_SEATS)
	ResponseEntity<?> updateShow(@RequestBody ShowSeatsDTO show) throws CommonAPIException {
		logger.info("createShow:Show updation started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_UPDATED, HttpStatus.OK, theatreService.udpateShow(show));
	}
}
