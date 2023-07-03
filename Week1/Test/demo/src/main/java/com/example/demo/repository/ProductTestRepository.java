package com.example.demo.repository;

import com.example.demo.model.ProductTest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<ProductTest> findByTittleContainingOrCodeContainingOrCategory_NameContaining(@NotBlank(message = "Tittle is required") String tittle, @NotBlank(message = "Code is required") String code, String category_name, Pageable pageable);

    Optional<ProductTest> findById(long id);
}
