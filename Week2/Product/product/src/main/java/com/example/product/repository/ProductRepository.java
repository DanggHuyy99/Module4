package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from Product  p where " +
    "p.name like : search or "+
    "p.category.name like : search")
    Page<Product> searchEverthingOnTheWorld(String search, Pageable pageable);

    Page<Product> findByNameContainingOrCategory_NameContaining(String name, String category_name, Pageable pageable);
}
