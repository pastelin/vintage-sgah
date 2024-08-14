package com.sgahs.springboot.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MontoValidoValidator implements ConstraintValidator<MontoValido, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		return value != null && value > 0.0;
	}
}
