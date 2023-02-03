/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.controllers;

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

import com.sapient.movie.consts.CommonMessageConstants;
import com.sapient.movie.consts.MiscConstants;
import com.sapient.movie.consts.UrlConstants;
import com.sapient.movie.exceptions.CommonAPIException;
import com.sapient.movie.models.MovieDTO;
import com.sapient.movie.services.MovieService;
import com.sapient.movie.utils.AppWebUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * The Class MovieController.
 * @author Sandeep Kumar
 */
@Api(tags = {MiscConstants.SG_MOVIE})
@SwaggerDefinition(tags = {@Tag(name = MiscConstants.SG_MOVIE, description = "This API provides endpoints for Movie management")})
@RestController
@RequestMapping(UrlConstants.MOVIE_API_BASE + UrlConstants.V1 + UrlConstants.API)
public class MovieController {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(MovieController.class);
	
	/** The movie service. */
	@Autowired
	private MovieService movieService;

	/**
	 * Creates the.
	 *
	 * @param movie the movie
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.POST, value = "Create Movie", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Movie created successfully", response = ResponseEntity.class)
			} )	
	@PostMapping
	ResponseEntity<?> create(@RequestBody MovieDTO movie) throws CommonAPIException {
		logger.info("create:Movie creation started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, movieService.create(movie));
	}
	
	/**
	 * Update.
	 *
	 * @param movie the movie
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.PUT, value = "Update Movie", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Movie updated successfully", response = ResponseEntity.class)
			} )	
	@PutMapping
	ResponseEntity<?> update(@RequestBody MovieDTO movie) throws CommonAPIException {
		logger.info("update:Movie update started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_UPDATED, HttpStatus.OK, movieService.update(movie));
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.DELETE, value = "Delete Movie", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 202, message = "Movie deleted successfully", response = ResponseEntity.class)
			} )	
	@DeleteMapping
	ResponseEntity<?> delete(@RequestParam Long id) throws CommonAPIException {
		logger.info("delete:Movie delete started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.ACCEPTED, movieService.delete(id));
	}
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Fetch Movie by ID", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Movie fetched successfully", response = ResponseEntity.class)
			} )	
	@GetMapping
	ResponseEntity<?> getById(@RequestParam Long id) throws CommonAPIException {
		logger.info("getById:Movie fetch by id started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, movieService.getById(id));
	}
	
	/**
	 * Gets the all.
	 *
	 * @param query    the query
	 * @param pageable the pageable
	 * @return the all
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Fetch Movie by all criteria", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Movie fetched successfully", response = ResponseEntity.class)
			} )	
	@GetMapping(UrlConstants.ALL)
	ResponseEntity<?> getAll(@RequestParam(name = MiscConstants.QUERY, required = false) String query, Pageable pageable) {
		logger.info("getAll:Movie get all started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK,  movieService.getAll(query, pageable));
	}
}