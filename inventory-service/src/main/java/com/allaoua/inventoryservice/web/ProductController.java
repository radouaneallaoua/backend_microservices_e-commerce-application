package com.allaoua.inventoryservice.web;


import com.allaoua.inventoryservice.dto.ProductColorResponseDto;
import com.allaoua.inventoryservice.dto.ProductRequestDto;
import com.allaoua.inventoryservice.dto.ProductResponseDto;
import com.allaoua.inventoryservice.service.ProductColorService;
import com.allaoua.inventoryservice.service.ProductImageService;
import com.allaoua.inventoryservice.service.ProductService;
import com.allaoua.inventoryservice.service.ProductSizeService;
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
    private final ProductColorService productColorService;
    private final ProductImageService productImageService;
    private final ProductSizeService productSizeService;
    public ProductController(ProductService productService, ProductColorService productColorService, ProductImageService productImageService, ProductSizeService productSizeService) {
        this.productService = productService;
        this.productColorService = productColorService;
        this.productImageService = productImageService;
        this.productSizeService = productSizeService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryId(@PathVariable Long categoryId) {
        return productService.getAllProductsWithCategoryId(categoryId);
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPage(@PathVariable Long brandId) {
        return productService.getAllProductsWithBrandId(brandId);
    }

    @GetMapping("/name-contains")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsByNameContaining(@RequestParam String name) {
        return productService.getAllProductsByNameContaining(name);
    }

    @GetMapping("/{id}/colors")
    public ResponseEntity<List<ProductColorResponseDto>> getProductColors(@PathVariable String id) {
        return productColorService.getAllProductColorsByProductId(id);
    }



    @GetMapping("/category/{categoryId}/with-page")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPage(@PathVariable Long categoryId,@RequestParam (name = "page",required = false) int page,@RequestParam (name = "size",required = false) int size) {
        return productService.getAllProductsWithCategoryIdAndPage(categoryId, page, size);
    }


    @GetMapping("/price-between")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithPriceBetweenAndPage(@RequestParam (name = "minPrice") double minPrice,@RequestParam (name = "maxPrice") double maxPrice,@RequestParam (name = "page",required = false) int page,@RequestParam (name = "size",required = false) int size) {
        return productService.getAllProductsWithPriceBetweenAndPage(minPrice,maxPrice, page, size);
    }

    @GetMapping("/category/{categoryId}/price-desc")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPriceDesc(@PathVariable Long categoryId) {
        return productService.getAllProductsWithCategoryIdAndPriceDesc(categoryId);
    }
    @GetMapping("/category/{categoryId}/price-asc")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsWithCategoryIdAndPriceAsc(@PathVariable Long categoryId) {
        return productService.getAllProductsWithCategoryIdAndPriceAsc(categoryId);
    }


    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> saveProduct(@ModelAttribute() ProductRequestDto productRequestDto) throws IOException {
        return productService.saveProduct(productRequestDto);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable String productId,
                                                            @RequestParam(name = "name",required = false) String name,
                                                            @RequestParam(name = "description",required = false) String description,
                                                            @RequestParam(name = "price",required = false) double price,
                                                            @RequestParam(name = "oldPrice",required = false) double oldPrice,
                                                            @RequestParam(name = "quantity",required = false) int quantity,
                                                            @RequestParam(name = "categoryId",required = false) Long categoryId,
                                                            @RequestParam(name = "brandId",required = false) Long brandId,
                                                            @RequestParam(name = "images",required = false) List<MultipartFile> images,
                                                            @RequestParam(name = "colorsIds",required = false)  List<Long> colorsIds,
                                                            @RequestParam(name = "sizesIds",required = false) List<Long> sizesIds) {
                return productService.updateProduct(productId ,name,description,price,oldPrice,quantity,categoryId,brandId,images,colorsIds,sizesIds);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }


    @GetMapping(value = "/product-image/{productImageId}/image",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getSingleProductImageWithId(@PathVariable Long productImageId) throws IOException {
        return productImageService.getSingleProductImageWithId(productImageId);
    }




}
