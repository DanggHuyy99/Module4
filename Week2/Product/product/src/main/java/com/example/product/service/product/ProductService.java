package com.example.product.service.product;

import com.example.product.Ulti.AppUtils;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.product.request.ProductSaveRequest;
import com.example.product.service.product.response.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductListResponse> findAllWithSearchAndPaging(String search, Pageable pageable) {
        return productRepository
                .findByNameContainingOrCategory_NameContaining(search, search, pageable)
                .map(product -> {
                  var response = AppUtils.mapper.map(product, ProductListResponse.class);
                  response.setCategoryName(product.getCategory().getName());
                  return response;
                });
    }

    public void create(ProductSaveRequest request) {
        Product newProduct = AppUtils.mapper.map(request, Product.class);

        productRepository.save(newProduct);
    }

    public void update(Product request, Long id) {
        Product newProduct = AppUtils.mapper.map(request, Product.class);
        newProduct.setId(id);

        productRepository.save(newProduct);
    }
}
