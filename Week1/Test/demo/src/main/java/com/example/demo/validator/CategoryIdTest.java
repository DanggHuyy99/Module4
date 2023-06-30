package com.example.demo.validator;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRespository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryIdTest implements ConstraintValidator<ValidateIDTest, Integer> {

    private final  CategoryRespository categoryRepository;


    public CategoryIdTest(CategoryRespository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initialize(ValidateIDTest constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer category, ConstraintValidatorContext context) {
        if (category == null) {
            return false;
        }

        return categoryRepository.existsById(Long.valueOf(category));
    }

}
