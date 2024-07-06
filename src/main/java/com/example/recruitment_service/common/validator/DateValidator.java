package com.example.recruitment_service.common.validator;

import com.example.recruitment_service.common.annotation.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(ValidDate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String birthday, ConstraintValidatorContext context) {
        if (birthday == null) {
            return false;
        }
        try {
            LocalDate.parse(birthday, DATE_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
