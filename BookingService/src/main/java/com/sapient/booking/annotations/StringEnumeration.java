/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sapient.booking.validators.StringEnumerationValidator;

/**
 * The Interface StringEnumeration.
 * @author Sandeep Kumar
 */
@Documented
@Constraint(validatedBy = StringEnumerationValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringEnumeration {

	 /**
	 * Message.
	 *
	 * @return the string
	 */
 	String message() default "Value not as per enumeration";
	  
  	/**
	 * Groups.
	 *
	 * @return the class[]
	 */
  	Class<?>[] groups() default {};
	  
  	/**
	 * Payload.
	 *
	 * @return the class<? extends payload>[]
	 */
  	Class<? extends Payload>[] payload() default {};

	  /**
	 * Enum class.
	 *
	 * @return the class<? extends enum<?>>
	 */
  	Class<? extends Enum<?>> enumClass();
	  
}
