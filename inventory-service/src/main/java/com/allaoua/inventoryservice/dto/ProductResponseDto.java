package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class ProductResponseDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private double oldPrice;
    private int quantity;
    private CategoryResponseDto category;
    private BrandResponseDto brand;
    private List<ProductImageResponseDto> images;
    private List<ProductColorResponseDto> colors;
    private List<ProductSizeResponseDto> sizes;

}
