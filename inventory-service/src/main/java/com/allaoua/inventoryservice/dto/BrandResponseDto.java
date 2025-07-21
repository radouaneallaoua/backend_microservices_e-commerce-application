package com.allaoua.inventoryservice.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class BrandResponseDto {
    private Long id;
    private String name;
}
