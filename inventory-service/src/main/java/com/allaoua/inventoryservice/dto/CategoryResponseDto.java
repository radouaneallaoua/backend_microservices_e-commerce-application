package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Long parentCategoryId;
}
