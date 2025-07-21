package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class ProductImageResponseDto {
    private Long id;
    private String productId;
    private String image;
}
