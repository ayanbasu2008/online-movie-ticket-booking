/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface EndpointMetadata.
 * @author Sandeep Kumar
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EndpointMetadata {

	/**
	 * Module name.
	 *
	 * @return the string
	 */
	String moduleName() default "";
	
	/**
	 * Action.
	 *
	 * @return the string
	 */
	String action() default "";
}