package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.ProductResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private double oldPrice;
    private int quantity;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductColor> colors;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductSize> productSizes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductImage> images;

    public ProductResponseDto toDto() {
        return ProductResponseDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .oldPrice(oldPrice)
                .quantity(quantity)
                .category(category.toDto())
                .brand(brand.toDto())
                .colors(colors!=null? colors.stream().map(ProductColor::toDto).toList():null)
                .sizes(productSizes!=null? productSizes.stream().map(ProductSize::toDto).toList():null)
                .images(images!=null? images.stream().map(ProductImage::toDto).toList():null)
                .build();
    }

}
