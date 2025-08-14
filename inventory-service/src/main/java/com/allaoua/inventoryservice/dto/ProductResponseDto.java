package com.allaoua.inventoryservice.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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
    private List<ColorResponseDto> colors;
    private List<SizeResponseDto> sizes;

}
