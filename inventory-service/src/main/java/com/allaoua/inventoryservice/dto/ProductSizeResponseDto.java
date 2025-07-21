package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class ProductSizeResponseDto {
    private String productId;
    private Long sizeId;
}
