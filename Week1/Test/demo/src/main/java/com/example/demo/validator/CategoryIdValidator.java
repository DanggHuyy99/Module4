package com.example.demo.validator;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class CategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Autowired
    private CategoryService categoryService;

    @Override
    public void initialize(ValidCategoryId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Category category, ConstraintValidatorContext constraintValidatorContext) {
        if (category == null)
            return false;

        Category cate = categoryService.findById(category.getId());
        return cate != null;
    }


}
