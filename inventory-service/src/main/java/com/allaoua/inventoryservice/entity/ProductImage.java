package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.ProductImageResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageURL;

    @ManyToOne
    private Product product;

    public ProductImageResponseDto toDto() {
        return ProductImageResponseDto.builder()
                .image(imageURL)
                .productId(product.getId())
                .id(id)
                .build();
    }

}
