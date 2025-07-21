package com.allaoua.inventoryservice.repository;


import com.allaoua.inventoryservice.entity.Color;
import com.allaoua.inventoryservice.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(String productId);
}
