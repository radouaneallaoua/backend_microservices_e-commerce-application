package com.allaoua.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class ProductRequestDto {
    @NotBlank
    private String name;

    private String description;
    @NotNull
    @Min(value = 0,message = "price must be greater than 0")
    private double price;

    @Min(value = 0,message = "price must be greater than 0")
    private double oldPrice;

    @Min(value = 0,message = "quantity must be greater than 0")
    private int quantity;

    @NotNull
    private Long categoryId;

    private Long brandId;

    @NotNull
    private List<MultipartFile> images;
    private List<Long> colorsIds;
    private List<Long> sizesIds;


}
