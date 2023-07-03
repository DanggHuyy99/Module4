package com.example.product.service.bill.request;

import com.example.product.service.product.request.SelectOptionRequest;

public class BillDetailSaveRequest {
    private SelectOptionRequest product;

    private String price;
    private String quantity;

    public BillDetailSaveRequest() {
        product = new SelectOptionRequest();
    }

    public SelectOptionRequest getProduct() {
        return product;
    }

    public void setProduct(SelectOptionRequest product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
