package com.epam.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements
        ConstraintValidator<DateValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate != null && localDate.isBefore(LocalDate.now());
    }
}
