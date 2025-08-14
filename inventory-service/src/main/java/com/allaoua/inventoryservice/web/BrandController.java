package com.allaoua.inventoryservice.web;


import com.allaoua.inventoryservice.dto.BrandRequestDto;
import com.allaoua.inventoryservice.dto.BrandResponseDto;
import com.allaoua.inventoryservice.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@CrossOrigin(value = "*",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public ResponseEntity<List<BrandResponseDto>> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping("")
    public ResponseEntity<BrandResponseDto> saveBrand(@RequestBody(required = true) BrandRequestDto brandRequestDto) {
        return brandService.saveBrand(brandRequestDto);
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandResponseDto> updateBrand(@PathVariable Long brandId, @RequestBody(required = true) BrandRequestDto brandRequestDto) {
        return brandService.updateBrand(brandId ,brandRequestDto);
    }
    @DeleteMapping("/{brandId}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long brandId) {
        return brandService.deleteBrand(brandId);
    }

}
