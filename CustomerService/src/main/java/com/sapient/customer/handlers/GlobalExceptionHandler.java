/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.handlers;

import java.text.ParseException;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.customer.consts.Exceptions;
import com.sapient.customer.exceptions.CommonAPIException;
import com.sapient.customer.models.ApiExceptionDTO;
import com.sapient.customer.utils.AppWebUtils;

/** This class handle all api's exception globally. 
 * @author Sandeep Kumar
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());
	
	/** The message source. */
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Gets the localized message.
	 *
	 * @param exception the exception
	 * @param args      the args
	 * @return the localized message
	 */
	private String getLocalizedMessage(Exceptions exception, Object[] args) {
		return AppWebUtils.getMessage(messageSource, exception.getMessagekey(), exception.getDefaultMessage(), args);
	}

	/**
	 * Builds the response DTO.
	 *
	 * @param ex        the ex
	 * @param exception the exception
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseDTO(Throwable ex, Exceptions exception) {
		return buildResponseEntity(new ApiExceptionDTO(exception.getStatus(), getLocalizedMessage(exception, null), ex, exception.getCode()));
	}
	
	/**
	 * Builds the response DTO.
	 *
	 * @param ex        the ex
	 * @param exception the exception
	 * @param args      the args
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseDTO(Throwable ex, Exceptions exception, Object[] args) {
		return buildResponseEntity(new ApiExceptionDTO(exception.getStatus(), getLocalizedMessage(exception, args), ex, exception.getCode()));
	}

	/**
	 * Builds the response DTO.
	 *
	 * @param ex        the ex
	 * @param exception the exception
	 * @param args      the args
	 * @param suffix    the suffix
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseDTO(Throwable ex, Exceptions exception, Object[] args, String suffix) {
		return buildResponseEntity(new ApiExceptionDTO(exception.getStatus(), getLocalizedMessage(exception, args) + suffix, ex, exception.getCode()));
	}
	
	/**
	 * Builds the response entity.
	 *
	 * @param apiError the api error
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiExceptionDTO apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	/**
	 * Handle IllegalStateException.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(value = IllegalStateException.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
		LOGGER.throwing("GlobalEx", "IllegalStateException", ex);
		return buildResponseDTO(ex, Exceptions.E0001);
	}

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required'
	 * request parameter is missing.
	 *
	 * @param ex
	 *            MissingServletRequestParameterException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiExceptionDTO object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "MissingServletRequestParameterException", ex);
		return buildResponseDTO(ex, Exceptions.E0001, new String[] {ex.getParameterName()});
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
	 * invalid as well.
	 *
	 * @param ex
	 *            HttpMediaTypeNotSupportedException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiExceptionDTO object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "HttpMediaTypeNotSupportedException", ex);
		StringBuilder builder = new StringBuilder();
		ex.getSupportedMediaTypes().forEach( t -> builder.append(t).append(", "));
		return buildResponseDTO(ex, Exceptions.E0003, new String[] {ex.getContentType().toString()}, builder.toString()); 
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex
	 *            the MethodArgumentNotValidException that is thrown when @Valid
	 *            validation fails
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiExceptionDTO object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "MethodArgumentNotValidException", ex);
		ResponseEntity<Object> retVal = buildResponseDTO(ex, Exceptions.E0004);
		ApiExceptionDTO apiError = (ApiExceptionDTO)retVal.getBody();
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return retVal;
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
	 * fails.
	 *
	 * @param ex
	 *            the ConstraintViolationException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
		LOGGER.throwing("GlobalEx", "ConstraintViolationException", ex);
		ResponseEntity<Object> retVal = buildResponseDTO(ex, Exceptions.E0005);
		ApiExceptionDTO apiError = (ApiExceptionDTO)retVal.getBody();
		apiError.addValidationErrors(ex.getConstraintViolations());
		return retVal;
	}

	/**
	 * Handles DataAccessException. Created to encapsulate errors with more detail
	 * than DataAccessException.
	 *
	 * @param ex
	 *            the DataAccessException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<Object> handleSQLGrammarException(DataAccessException ex) {
		LOGGER.throwing("GlobalEx", "DataAccessException", ex);
		return buildResponseDTO(ex, Exceptions.E0006);
	}

	/**
	 * Handles PersistenceException. Created to encapsulate errors with more detail
	 * than PersistenceException.
	 *
	 * @param ex
	 *            the PersistenceException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(PersistenceException.class)
	protected ResponseEntity<Object> handleSQLGrammarException(PersistenceException ex) {
		LOGGER.throwing("GlobalEx", "PersistenceException", ex);
		return buildResponseDTO(ex, Exceptions.E0007);
	}

	/**
	 * Handles SQLGrammarException. Created to encapsulate errors with more detail
	 * than org.hibernate.exception.SQLGrammarException.
	 *
	 * @param ex
	 *            the SQLGrammarException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(SQLGrammarException.class)
	protected ResponseEntity<Object> handleSQLGrammarException(SQLGrammarException ex) {
		LOGGER.throwing("GlobalEx", "SQLGrammarException", ex);
		return buildResponseDTO(ex, Exceptions.E0008);
	}
	
	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex
	 *            HttpMessageNotReadableException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiExceptionDTO object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "HttpMessageNotReadableException", ex);
		return buildResponseDTO(ex, Exceptions.E0009);
	}

	/**
	 * Handle HttpMessageNotWritableException.
	 *
	 * @param ex
	 *            HttpMessageNotWritableException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiExceptionDTO object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "HttpMessageNotWritableException", ex);
		return buildResponseDTO(ex, Exceptions.E0010);
	}

	/**
	 * Handle javax.persistence.EntityNotFoundException
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
		LOGGER.throwing("GlobalEx", "EntityNotFoundException", ex);
		return buildResponseDTO(ex, Exceptions.E0011);
	}

	/**
	 * Handle PropertyValueException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex
	 *            the PropertyValueException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(PropertyValueException.class)
	protected ResponseEntity<Object> handlePropertyValueException(PropertyValueException ex) {
		LOGGER.throwing("GlobalEx", "PropertyValueException", ex);
		return buildResponseDTO(ex, Exceptions.E0012);
	}
	
	
	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex
	 *            the DataIntegrityViolationException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		LOGGER.throwing("GlobalEx", "DataIntegrityViolationException", ex);
		return buildResponseDTO(ex, Exceptions.E0013);
	}

	/**
	 * Handle CommonAPIException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex
	 *            the CommonAPIException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(CommonAPIException.class)
	protected ResponseEntity<Object> handleActionNotSupportedException(CommonAPIException ex) {
		LOGGER.throwing("GlobalEx", "CommonAPIException", ex);
		ApiExceptionDTO apiError = new ApiExceptionDTO(ex.getExceptionDetail().getStatus(), AppWebUtils.getMessage(messageSource, ex.getExceptionDetail().getMessagekey(), ex.getExceptionDetail().getDefaultMessage(), null), ex.getCause());
		return buildResponseEntity(apiError);
	}
	
	/**
	 * Handle Exception, handle generic Exception.class
	 *
	 * @param ex      the Exception
	 * @param request the request
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		LOGGER.throwing("GlobalEx", "MethodArgumentTypeMismatchException", ex);
		return buildResponseDTO(ex, Exceptions.E0014);
	}

	/* Handles NoHandlerFoundException
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleNoHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "NoHandlerFoundException", ex);
		return buildResponseDTO(ex, Exceptions.E0015, null, ex.getHttpMethod() + " " + ex.getRequestURL());
	}

	/* Handles MethodNotSupported
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleHttpRequestMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.throwing("GlobalEx", "HttpRequestMethodNotSupportedException", ex);
		StringBuilder builder = new StringBuilder();
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		return buildResponseDTO(ex, Exceptions.E0003, new String[] {ex.getMethod()}, builder.toString()); 
	}

	/**
	 * Handles NumberFormatException. Created to encapsulate errors with more detail
	 * than NumberFormatException.
	 *
	 * @param ex
	 *            the NumberFormatException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(NumberFormatException.class)
	protected ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
		LOGGER.throwing("GlobalEx", "NumberFormatException", ex);
		return buildResponseDTO(ex, Exceptions.E0017);
	}

	/**
	 * Handles ParseException. If date format is not proper then this will handle
	 * it.
	 *
	 * @param ex
	 *            the ParseException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(ParseException.class)
	protected ResponseEntity<Object> handleParseException(ParseException ex) {
		LOGGER.throwing("GlobalEx", "ParseException", ex);
		return buildResponseDTO(ex, Exceptions.E0018);
	}

	/**
	 * Handles IllegalArgumentException.
	 *
	 * @param ex the IllegalArgumentException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		LOGGER.throwing("GlobalEx", "IllegalArgumentException", ex);
		return buildResponseDTO(ex, Exceptions.E0019);
	}

	/**
	 * Handles ResourceNotFoundException.
	 *
	 * @param ex the ResourceNotFoundException
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		LOGGER.throwing("GlobalEx", "ResourceNotFoundException", ex);
		return buildResponseDTO(ex, Exceptions.E0020);
	}
	
	/**
	 * Handles RuntimeException.
	 *
	 * @param ex Runtime exception
	 * @return the ApiExceptionDTO object
	 */

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
		LOGGER.throwing("GlobalEx", "RuntimeException", ex);
		return buildResponseDTO(ex, Exceptions.E0021);
	}

	/**
	 * Handles NullPointerException.
	 *
	 * @param ex NullPointerException exception
	 * @return the ApiExceptionDTO object
	 */
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
		LOGGER.throwing("GlobalEx", "NullPointerException", ex);
		return buildResponseDTO(ex, Exceptions.E0022);
	}
	
	/**
	 * Handles All the exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		LOGGER.throwing("GlobalEx", "Exception", ex);
		return buildResponseDTO(ex, Exceptions.E0023);
	}
}