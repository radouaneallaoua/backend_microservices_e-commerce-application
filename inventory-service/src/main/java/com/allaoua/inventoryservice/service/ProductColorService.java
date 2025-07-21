package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.ProductColorResponseDto;
import com.allaoua.inventoryservice.dto.SizeRequestDto;
import com.allaoua.inventoryservice.dto.SizeResponseDto;
import com.allaoua.inventoryservice.entity.ProductColor;
import com.allaoua.inventoryservice.entity.Size;
import com.allaoua.inventoryservice.exception.SizeNotFoundException;
import com.allaoua.inventoryservice.repository.ProductColorRepository;
import com.allaoua.inventoryservice.repository.SizeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductColorService {
   final private ProductColorRepository productColorRepository;

    public ProductColorService(ProductColorRepository productColorRepository) {
        this.productColorRepository = productColorRepository;
    }


    public ResponseEntity<List<ProductColorResponseDto>> getAllProductColors() {
        return ResponseEntity.ok(productColorRepository.findAll().stream().map(ProductColor::toDto).toList());
    }

    public ResponseEntity<List<ProductColorResponseDto>> getAllProductColorsByProductId(String productId) {
        return ResponseEntity.ok(productColorRepository.findByProductId(productId).stream().map(ProductColor::toDto).toList());
    }

    public ResponseEntity<List<ProductColorResponseDto>> getAllProductColorsByColorId(Long colorId) {
        return ResponseEntity.ok(productColorRepository.findByColorId(colorId).stream().map(ProductColor::toDto).toList());
    }




}
