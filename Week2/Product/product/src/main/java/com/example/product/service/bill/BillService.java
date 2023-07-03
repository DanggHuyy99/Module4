package com.example.product.service.bill;

import com.example.product.Ulti.AppUtils;
import com.example.product.model.Bill;
import com.example.product.repository.BillRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.service.bill.request.BillSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final ProductRepository productRepository;

    private final BillRepository billRepository;


    public BillService(ProductRepository productRepository, BillRepository billRepository) {
        this.productRepository = productRepository;
        this.billRepository = billRepository;
    }

    public void create(BillSaveRequest request){
        Bill bill = AppUtils.mapper.map(request, Bill.class);
        for (var item : bill.getBillDetails()){
            var product = productRepository.findById(item.getProduct().getId()).orElseThrow();
            item.setPrice(product.getPrice());
            item.setNameProduct(product.getName());
        }
    }
}
