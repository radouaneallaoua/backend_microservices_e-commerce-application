package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.ProductColorResponseDto;
import com.allaoua.inventoryservice.dto.ProductSizeResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductColor {
    @EmbeddedId
    private  ProductColorKey productColorKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("colorId")
    @JoinColumn(name = "color_id")
    private Color color;

    public ProductColorResponseDto toDto() {
        return ProductColorResponseDto.builder()
                .colorId(color.getId())
                .productId(product.getId())
                .build();
    }

}
