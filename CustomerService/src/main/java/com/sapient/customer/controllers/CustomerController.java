/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.controllers;

import javax.validation.Valid;

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

import com.sapient.customer.consts.CommonMessageConstants;
import com.sapient.customer.consts.MiscConstants;
import com.sapient.customer.consts.UrlConstants;
import com.sapient.customer.exceptions.CommonAPIException;
import com.sapient.customer.models.CustomerDTO;
import com.sapient.customer.services.CustomerService;
import com.sapient.customer.utils.AppWebUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * The Class CustomerController.
 * @author Sandeep Kumar
 */
@Api(tags = {MiscConstants.SG_CUSTOMER})
@SwaggerDefinition(tags = {@Tag(name = MiscConstants.SG_CUSTOMER, description = "This API provides endpoints for customer management")})
@RestController
@RequestMapping(UrlConstants.CUSTOMER_API_BASE + UrlConstants.V1 + UrlConstants.API)
public class CustomerController {

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	/** The customer service. */
	@Autowired
	private CustomerService customerService;
	
	/**
	 * Creates the.
	 *
	 * @param customer the customer
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.POST, value = "Create Customer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 201, message = "Customer created successfully", response = ResponseEntity.class)
			} )	
	@PostMapping
	ResponseEntity<?> create(@Valid @RequestBody CustomerDTO customer) throws CommonAPIException {
		logger.info("create:Creation started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_CREATED, HttpStatus.CREATED, customerService.create(customer));
	}
	
	/**
	 * Update.
	 *
	 * @param customer the customer
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.PUT, value = "Update Customer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Customer updated successfully", response = ResponseEntity.class)
			} )	
	@PutMapping
	ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customer) throws CommonAPIException {
		logger.info("update:Update started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_UPDATED, HttpStatus.OK, customerService.update(customer));
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.DELETE, value = "Delete Customer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 202, message = "Customer deleted successfully", response = ResponseEntity.class)
			} )	
	@DeleteMapping
	ResponseEntity<?> delete(@RequestParam Long id) throws CommonAPIException {
		logger.info("delete:Delete started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_DELETED, HttpStatus.ACCEPTED, customerService.delete(id));
	}

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws CommonAPIException the common API exception
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Fetch Customer by ID", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Customer fetched successfully", response = ResponseEntity.class)
			} )	
	@GetMapping
	ResponseEntity<?> getById(@RequestParam Long id) throws CommonAPIException {
		logger.info("getById:Fetch started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, customerService.getById(id));
	}
	
	/**
	 * Gets the all.
	 *
	 * @param query    the query
	 * @param pageable the pageable
	 * @return the all
	 */
	@ApiOperation(httpMethod = MiscConstants.GET, value = "Fetch Customer by all criteria", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses( value = {
			@ApiResponse(code = 200, message = "Customer fetched successfully", response = ResponseEntity.class)
			} )	
	@GetMapping(UrlConstants.ALL)
	ResponseEntity<?> getAll(@RequestParam(name = MiscConstants.QUERY, required = false) String query, Pageable pageable) {
		logger.info("getAll:Fetch started");
		return AppWebUtils.buildResponse(CommonMessageConstants.SUCCESS_FETCHED, HttpStatus.OK, customerService.getAll(query, pageable));
	}
}
