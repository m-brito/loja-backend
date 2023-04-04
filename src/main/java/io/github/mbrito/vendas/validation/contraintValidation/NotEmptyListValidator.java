package io.github.mbrito.vendas.validation.contraintValidation;

import java.util.List;

import io.github.mbrito.vendas.validation.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		return value != null && !value.isEmpty();
	}
	
	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		
	}
	
}
