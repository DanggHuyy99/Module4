package com.example.demo.repository;

import com.example.demo.model.ProductTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductTestRepository extends JpaRepository<ProductTest, Long> {
    @Query(value = "SELECT p from ProductTest p where "+
    "p.code like :search or " +
    "p.tittle like :search or "+
    "p.category.name like :search")
    List<ProductTest> searchEverything(String search);
    List<ProductTest> findByTittleContainingOrCodeContainingOrCategory_NameContaining(String tittle, String code, String category_name);

    Optional<ProductTest> findById(long id);
}
