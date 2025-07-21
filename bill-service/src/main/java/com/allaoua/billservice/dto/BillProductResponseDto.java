package com.allaoua.billservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class BillProductResponseDto {
    private Long id;
    private String productId;
    private int quantity;
    private Long sizeId;
    private Long colorId;
    private String billId;


}
