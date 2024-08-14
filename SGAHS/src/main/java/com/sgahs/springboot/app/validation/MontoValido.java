package com.sgahs.springboot.app.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MontoValidoValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface MontoValido {
	
	String message() default "El monto debe ser mayor a $0";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
