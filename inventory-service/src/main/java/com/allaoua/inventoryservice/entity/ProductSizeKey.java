package com.allaoua.inventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter @Getter @AllArgsConstructor @NoArgsConstructor  @Builder
public class ProductSizeKey {
    @Column(name = "product_id")
    private String productId;

    @Column(name = "size_id")
    private Long sizeId;
}
