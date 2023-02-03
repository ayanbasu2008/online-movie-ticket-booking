/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/** This class represents json error message when any api's fails.
 * @author Sandeep Kumar
 *
 */
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiExceptionDTO {

    /** The status. */
    private HttpStatus status;
    
    /** The response time. */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date responseTime;
    
    /** The message. */
    private String message;
    
    /** The debug message. */
    private String debugMessage;
    
    /** The sub errors. */
    private List<ApiSubError> subErrors;
    
    /** The code. */
    private String code;
    
    /**
	 * Gets the debug message.
	 *
	 * @return the debug message
	 */
    public String getDebugMessage() {
		return debugMessage;
	}

	/**
	 * Sets the debug message.
	 *
	 * @param debugMessage the new debug message
	 */
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
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
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return responseTime;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.responseTime = timestamp;
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
	 * Gets the sub errors.
	 *
	 * @return the sub errors
	 */
	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}

	/**
	 * Sets the sub errors.
	 *
	 * @param subErrors the new sub errors
	 */
	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}

	/**
	 * Instantiates a new api exception DTO.
	 */
	private ApiExceptionDTO() {
        responseTime = new Date();
    }

    /**
	 * Instantiates a new api exception DTO.
	 *
	 * @param status the status
	 */
    public ApiExceptionDTO(HttpStatus status) {
        this();
        this.status = status;
    }

    /**
	 * Instantiates a new api exception DTO.
	 *
	 * @param status the status
	 * @param ex     the ex
	 */
    public ApiExceptionDTO(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    /**
	 * Instantiates a new api exception DTO.
	 *
	 * @param status  the status
	 * @param message the message
	 * @param ex      the ex
	 */
    public ApiExceptionDTO(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        if(ex != null) {
        	this.debugMessage = ex.getLocalizedMessage();
        }
    }

    /**
	 * Instantiates a new api exception DTO.
	 *
	 * @param status  the status
	 * @param message the message
	 * @param ex      the ex
	 * @param code    the code
	 */
    public ApiExceptionDTO(HttpStatus status, String message, Throwable ex, String code) {
        this();
        this.status = status;
        this.message = message;
        this.code = code;
        if(ex != null) {
        	this.debugMessage = ex.getLocalizedMessage();
        }
    }
    
    /**
	 * Adds the sub error.
	 *
	 * @param subError the sub error
	 */
    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    /**
	 * Adds the validation error.
	 *
	 * @param object        the object
	 * @param field         the field
	 * @param rejectedValue the rejected value
	 * @param message       the message
	 */
    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    /**
	 * Adds the validation error.
	 *
	 * @param object  the object
	 * @param message the message
	 */
    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    /**
	 * Adds the validation error.
	 *
	 * @param fieldError the field error
	 */
    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    /**
	 * Adds the validation errors.
	 *
	 * @param fieldErrors the field errors
	 */
    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    /**
	 * Adds the validation error.
	 *
	 * @param objectError the object error
	 */
    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    /**
	 * Adds the validation error.
	 *
	 * @param globalErrors the global errors
	 */
    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),cv.getInvalidValue(),cv.getMessage());
    }

    /**
	 * Adds the validation errors.
	 *
	 * @param constraintViolations the constraint violations
	 */
    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
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
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}




	/**
	 * The Interface ApiSubError.
	 */
	abstract interface ApiSubError {
    }

  
    /**
	 * The Class ApiValidationError.
	 */
    class ApiValidationError implements ApiSubError {
        
        /** The object. */
        private String object;
        
        /** The field. */
        private String field;
        
        /** The rejected value. */
        private Object rejectedValue;
        
        /** The message. */
        private String message;

        
        
        /**
		 * Instantiates a new api validation error.
		 *
		 * @param object        the object
		 * @param field         the field
		 * @param rejectedValue the rejected value
		 * @param message       the message
		 */
        public ApiValidationError(String object, String field, Object rejectedValue, String message) {
			super();
			this.object = object;
			this.field = field;
			this.rejectedValue = rejectedValue;
			this.message = message;
		}

		/**
		 * Instantiates a new api validation error.
		 *
		 * @param object  the object
		 * @param message the message
		 */
		ApiValidationError(String object, String message) {
            this.object = object;
            this.message = message;
        }

		/**
		 * Gets the object.
		 *
		 * @return the object
		 */
		public String getObject() {
			return object;
		}

		/**
		 * Sets the object.
		 *
		 * @param object the new object
		 */
		public void setObject(String object) {
			this.object = object;
		}

		/**
		 * Gets the field.
		 *
		 * @return the field
		 */
		public String getField() {
			return field;
		}

		/**
		 * Sets the field.
		 *
		 * @param field the new field
		 */
		public void setField(String field) {
			this.field = field;
		}

		/**
		 * Gets the rejected value.
		 *
		 * @return the rejected value
		 */
		public Object getRejectedValue() {
			return rejectedValue;
		}

		/**
		 * Sets the rejected value.
		 *
		 * @param rejectedValue the new rejected value
		 */
		public void setRejectedValue(Object rejectedValue) {
			this.rejectedValue = rejectedValue;
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
    }
}

class LowerCaseClassNameResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}