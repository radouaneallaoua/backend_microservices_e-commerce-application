package com.allaoua.billservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class BillProductRequestDto {
    @NotBlank
    private String productId;

    @Min(value = 0,message = "quantity must be greater than 0")
    private int quantity;

    private Long sizeId;
    private Long colorId;


}
