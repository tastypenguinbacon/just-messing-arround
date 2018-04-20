package io.tastypenguinbacon.pinger.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = PingValidator.class)
public @interface ValidPing {
    String message() default "";

    Class<?>[] grups() default {};

    Class<? extends Payload>[] payload() default {};
}
