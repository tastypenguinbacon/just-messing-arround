package io.tastypenguinbacon.pinger.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PingValidator implements ConstraintValidator<ValidPing, String> {
    @Override
    public void initialize(ValidPing validPing) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
