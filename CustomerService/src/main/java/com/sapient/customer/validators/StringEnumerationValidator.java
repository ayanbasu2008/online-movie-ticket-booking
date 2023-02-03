/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.customer.validators;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sapient.customer.annotations.StringEnumeration;

/**
 * The Class StringEnumerationValidator.
 * @author Sandeep Kumar
 */
public class StringEnumerationValidator implements ConstraintValidator<StringEnumeration, String> {

	/** The available enum names. */
	private Set<String> AVAILABLE_ENUM_NAMES;

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(StringEnumeration stringEnumeration) {
		Class<? extends Enum<?>> enumSelected = stringEnumeration.enumClass();
		Set<? extends Enum<?>> enumInstances = Stream.of(enumSelected.getEnumConstants()).collect(Collectors.toSet());
		AVAILABLE_ENUM_NAMES = enumInstances.stream().map(e -> e.toString()).collect(Collectors.toSet());
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else {
			return AVAILABLE_ENUM_NAMES.contains(value);
		}
	}

}
