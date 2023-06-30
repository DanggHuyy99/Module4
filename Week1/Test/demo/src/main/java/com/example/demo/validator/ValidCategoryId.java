package com.example.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(validatedBy = CategoryIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategoryId {
    String message() default "Category not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
