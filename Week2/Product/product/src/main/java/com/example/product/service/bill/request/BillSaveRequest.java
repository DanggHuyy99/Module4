package com.example.product.service.bill.request;

import com.example.product.service.customer.request.CustomerSaveRequest;
import jakarta.validation.Valid;

import java.util.List;

public class BillSaveRequest {
    private String purchaseDate;

    private @Valid CustomerSaveRequest customer;

    private List<@Valid BillSaveRequest> billDetails;

    public BillSaveRequest(List<@Valid BillSaveRequest> billDetails) {
        this.billDetails = billDetails;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public CustomerSaveRequest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerSaveRequest customer) {
        this.customer = customer;
    }

    public List<BillSaveRequest> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillSaveRequest> billDetails) {
        this.billDetails = billDetails;
    }
}
