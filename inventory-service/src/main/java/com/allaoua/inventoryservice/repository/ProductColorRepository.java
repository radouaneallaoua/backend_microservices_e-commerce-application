package com.allaoua.inventoryservice.repository;

import com.allaoua.inventoryservice.entity.ProductColor;
import com.allaoua.inventoryservice.entity.ProductColorKey;
import com.allaoua.inventoryservice.entity.ProductSize;
import com.allaoua.inventoryservice.entity.ProductSizeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorKey> {
   List<ProductColor> findByProductId(String productId);
   List<ProductColor> findByColorId(Long  colorId);
}
