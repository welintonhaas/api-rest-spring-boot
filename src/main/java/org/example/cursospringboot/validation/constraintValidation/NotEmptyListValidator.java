package org.example.cursospringboot.validation.constraintValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.cursospringboot.validation.NotEmptyList;

import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}
