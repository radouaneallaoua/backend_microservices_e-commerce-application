package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class SizeResponseDto {
    private Long id;
    private String label;
}
