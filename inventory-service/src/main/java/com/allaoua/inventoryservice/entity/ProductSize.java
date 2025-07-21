package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.ProductSizeResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSize {
    @EmbeddedId
    private  ProductSizeKey productSizeKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    @JoinColumn(name = "size_id")
    private Size size;

    public ProductSizeResponseDto toDto() {
        return ProductSizeResponseDto.builder()
                .productId(productSizeKey.getProductId())
                .sizeId(size.getId())
                .build();
    }

}
