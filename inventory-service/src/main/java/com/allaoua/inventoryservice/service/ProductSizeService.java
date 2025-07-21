package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.ProductSizeResponseDto;
import com.allaoua.inventoryservice.entity.ProductSize;
import com.allaoua.inventoryservice.repository.ProductSizeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductSizeService {
   final private ProductSizeRepository productSizeRepository;

    public ProductSizeService(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }


    public ResponseEntity<List<ProductSizeResponseDto>> getAllProductSizes() {
        return ResponseEntity.ok(productSizeRepository.findAll().stream().map(ProductSize::toDto).toList());
    }
    
    public ResponseEntity<List<ProductSizeResponseDto>> getAllProductSizesByProductId(String productId) {
        return ResponseEntity.ok(productSizeRepository.findByProductId(productId).stream().map(ProductSize::toDto).toList());
    }
    





}
