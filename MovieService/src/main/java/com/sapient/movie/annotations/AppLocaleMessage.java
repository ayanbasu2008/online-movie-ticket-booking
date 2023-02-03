/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.movie.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.sapient.movie.consts.MiscConstants;


/**
 * Annotation to add localized message for fields obtained from the
 * MessageSource for the current request's locale.
 *
 * @author Sandeep Kumar
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface AppLocaleMessage {

	/**
	 * It return the key value which will be used to generate the key of the
	 * message.
	 *
	 * @return Returns string key or class canonical name
	 */
	String key() default MiscConstants.EMPTY;

	/**
	 * It return the key Field name which will be used to generate the key of the
	 * message.
	 *
	 * @return Returns string key or class canonical name
	 */
	String keyField() default MiscConstants.EMPTY;
	
	
	/**
	 * It return the code field for the key for example, key key is AccountType then and code is 1 then
	 * final message key will be AccountType.1 
	 * @return Returns string
	 */
	String code() default MiscConstants.EMPTY;

	/**
	 * Default value for the field message.
	 *
	 * @return Return String
	 */
	String defaultValue() default MiscConstants.EMPTY;

	/**
	 * Additional code which will be added after code so complete localization key will be like 
	 * key.code.value=xx
	 * @return Return String
	 */
	String value() default MiscConstants.EMPTY;
	
	/**
	 * Default value from the same object's field .
	 *
	 * @return Return String
	 */
	String defaultValueField() default MiscConstants.EMPTY;
}