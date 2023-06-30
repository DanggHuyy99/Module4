package com.example.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryIdTest.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateIDTest {

    String message() default "Invalid category ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
