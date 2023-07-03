package com.example.product.service.product.request;

public class ProductSaveRequest {
    private String price;

    private String name;

    private SelectOptionRequest category;

    public ProductSaveRequest(String price, String name, SelectOptionRequest category) {
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SelectOptionRequest getCategory() {
        return category;
    }

    public void setCategory(SelectOptionRequest category) {
        this.category = category;
    }
}
