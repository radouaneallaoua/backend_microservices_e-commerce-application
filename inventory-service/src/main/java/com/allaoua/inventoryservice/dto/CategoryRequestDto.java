package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class CategoryRequestDto {
    @NotNull
    private String name;
    private String description;

    @NotNull
    private MultipartFile imageURL;

    private Long parentCategoryId;
}
