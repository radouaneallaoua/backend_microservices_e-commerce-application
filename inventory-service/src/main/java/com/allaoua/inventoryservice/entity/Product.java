package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.ProductResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
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

    @ManyToMany()
    @JoinTable( name = "product_colors",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors=new ArrayList<>();

    @ManyToMany()
    @JoinTable( name = "product_sizes",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<Size> sizes=new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductImage> images=new ArrayList<>();

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
                .colors(colors.stream().map(Color::toDto).toList())
                .sizes(sizes.stream().map(Size::toDto).toList())
                .images(images.stream().map(ProductImage::toDto).toList())
                .build();
    }

}
