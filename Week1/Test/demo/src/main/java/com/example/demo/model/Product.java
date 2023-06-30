package com.example.demo.model;

import com.example.demo.validator.ValidCategoryId;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.NumberFormat;

public class Product {
    private int id;

    @NotBlank(message = "Name must not be blank cái dmmm !!!!!!!!!!!")
    private String name;

    @Digits(integer = 10, fraction = 2, message = "Invalid price format(max 10 chữ số cái đmmmmmm)")
    @Positive(message = "Price must be positive")
    private long price;

    @ValidCategoryId
    private Category category;

    public Product(int id, String name, long price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
