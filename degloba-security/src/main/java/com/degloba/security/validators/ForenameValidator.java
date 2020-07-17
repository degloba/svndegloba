package com.degloba.security.validators;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.degloba.security.impl.spring.validation.annotations.Forename;

/**
 * @author degloba
 */
public class ForenameValidator implements ConstraintValidator<Forename, String> {
	private static final Pattern VALID = Pattern.compile("[\\p{L}'\\-,.]+");

	public void initialize(Forename constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return VALID.matcher(value).matches();
	}
}
