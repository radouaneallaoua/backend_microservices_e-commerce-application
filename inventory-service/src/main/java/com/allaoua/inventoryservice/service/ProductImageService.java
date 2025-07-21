package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.ProductImageResponseDto;
import com.allaoua.inventoryservice.entity.ProductImage;
import com.allaoua.inventoryservice.repository.ProductImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Transactional
public class ProductImageService {
   final private ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }


    public ResponseEntity<List<ProductImageResponseDto>> getAllProductImages() {
        return ResponseEntity.ok(productImageRepository.findAll().stream().map(ProductImage::toDto).toList());
    }
    
    public ResponseEntity<List<ProductImageResponseDto>> getAllProductImagesByProductId(String productId) {
        return ResponseEntity.ok(productImageRepository.findByProductId(productId).stream().map(ProductImage::toDto).toList());
    }


    public ResponseEntity<byte[]> getSingleProductImageWithId(Long productImageId) throws IOException {
        ProductImage productImage = productImageRepository.findById(productImageId).orElse(null);
        if (productImage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Files.readAllBytes(Path.of(URI.create(productImage.getImageURL()))));
    }
}
