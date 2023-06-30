package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.ProductTest;
import com.example.demo.repository.ProductTestRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService {
    private final ProductTestRepository productTestRepository;

    public ProductService(ProductTestRepository productTestRepository) {
        this.productTestRepository = productTestRepository;
    }

    private static int curID;
    private static List<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(curID++,"IPHONE 14 PROMAX", 99999, new Category("Tủ Lạnh")));
        products.add(new Product(curID++,"IPHONE 16 PROMAX", 98789999, new Category("Tủ Lạnh")));
    }



    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        product.setId(curID++);
        products.add(product);

    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(Product productUD) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == productUD.getId()) {
                product.setName(productUD.getName());
                product.setPrice(productUD.getPrice());
            }
        }
    }

    public void deleteProduct(int id){
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (id == product.getId()){
                products.remove(product);
            }
        }
    }

    public Page<ProductTest> getAllProducts(Pageable pageable) {
        return productTestRepository.findAll(pageable);
    }


}
