package com.allaoua.inventoryservice.web;
import com.allaoua.inventoryservice.dto.ProductRequestDto;
import com.allaoua.inventoryservice.dto.ProductResponseDto;
import com.allaoua.inventoryservice.service.ProductImageService;
import com.allaoua.inventoryservice.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;

    public ProductController(ProductService productService, ProductImageService productImageService) {
        this.productService = productService;
        this.productImageService = productImageService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPage(@PathVariable Long brandId, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size) {
        return productService.getAllProductsWithBrandId(brandId, page, size);
    }

    @GetMapping("/name-contains")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsByNameContaining(@RequestParam String name, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size) {
        return productService.getAllProductsByNameContaining(name, page, size);
    }


    @GetMapping("/price-between")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithPriceBetweenAndPage(@RequestParam(name = "minPrice") double minPrice, @RequestParam(name = "maxPrice") double maxPrice, @RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size) {
        return productService.getAllProductsWithPriceBetweenAndPage(minPrice, maxPrice, page, size);
    }


    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> saveProduct(@ModelAttribute() ProductRequestDto productRequestDto) throws IOException {
        return productService.saveProduct(productRequestDto);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable String productId,
                                                            @RequestParam(name = "name", required = false) String name,
                                                            @RequestParam(name = "description", required = false) String description,
                                                            @RequestParam(name = "price", required = false) double price,
                                                            @RequestParam(name = "oldPrice", required = false) double oldPrice,
                                                            @RequestParam(name = "quantity", required = false) int quantity,
                                                            @RequestParam(name = "categoryId", required = false) Long categoryId,
                                                            @RequestParam(name = "brandId", required = false) Long brandId,
                                                            @RequestParam(name = "images", required = false) List<MultipartFile> images,
                                                            @RequestParam(name = "colorsIds", required = false) List<Long> colorsIds,
                                                            @RequestParam(name = "sizesIds", required = false) List<Long> sizesIds) {
        return productService.updateProduct(productId, name, description, price, oldPrice, quantity, categoryId, brandId, images, colorsIds, sizesIds);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }


    @GetMapping(value = "/product-image/{productImageId}/image", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getSingleProductImageWithId(@PathVariable Long productImageId) throws IOException {
        return productImageService.getSingleProductImageWithId(productImageId);
    }


}
