package com.allaoua.inventoryservice.repository;

import com.allaoua.inventoryservice.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategoryId(Long  categoryId);
    List<Product> findByCategoryId(Long  categoryId, Pageable pageable);
    List<Product> findByCategoryId(Long  categoryId, Sort sort);
    List<Product> findByPriceBetween(double lower, double higher, Pageable pageable);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByBrandId(Long  brandId);



}
