package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class ProductColorResponseDto {
    private String productId;
    private Long colorId;
}
