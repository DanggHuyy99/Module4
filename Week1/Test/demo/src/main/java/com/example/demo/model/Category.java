package com.example.demo.model;

import com.example.demo.validator.ValidateIDTest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ValidateIDTest
    private Integer id;

    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<ProductTest> products;

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Set<ProductTest> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductTest> products) {
        this.products = products;
    }
}
