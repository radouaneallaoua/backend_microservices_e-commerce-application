package com.allaoua.inventoryservice.repository;

import com.allaoua.inventoryservice.entity.Brand;
import com.allaoua.inventoryservice.entity.ProductSize;
import com.allaoua.inventoryservice.entity.ProductSizeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeKey> {
   List<ProductSize> findByProductId(String productId);
}
