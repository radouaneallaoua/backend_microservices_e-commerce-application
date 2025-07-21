package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.BrandRequestDto;
import com.allaoua.inventoryservice.dto.BrandResponseDto;
import com.allaoua.inventoryservice.entity.Brand;
import com.allaoua.inventoryservice.exception.BrandNotFoundException;
import com.allaoua.inventoryservice.exception.CategoryNotFoundException;
import com.allaoua.inventoryservice.repository.BrandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService {
   final private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        return ResponseEntity.ok(brandRepository.findAll().stream().map(Brand::toDto).toList());
    }

    public ResponseEntity<BrandResponseDto> saveBrand(BrandRequestDto brandRequestDto) {
        Brand brand=Brand.builder()
                .name(brandRequestDto.getName())
                .build();
        return ResponseEntity.ok(brandRepository.save(brand).toDto());
    }

    public ResponseEntity<BrandResponseDto> getBrandById(Long brandId) {
        Brand brand=brandRepository.findById(brandId).orElseThrow(()-> new BrandNotFoundException("brand not found with id "+brandId));
        return ResponseEntity.ok(brand.toDto());
    }

    public ResponseEntity<BrandResponseDto> updateBrand(Long brandId, BrandRequestDto brandRequestDto) {
        Brand brand=brandRepository.findById(brandId).orElse(null);
        if(brand==null) {
            throw new BrandNotFoundException("brand not found with id "+brandId);
        }
        brand.setName(brandRequestDto.getName());
        return ResponseEntity.ok(brandRepository.save(brand).toDto());
    }

    public ResponseEntity<String> deleteBrand(Long brandId) {
       brandRepository.deleteById(brandId);
        return ResponseEntity.ok("brand successfully deleted");
    }


}
