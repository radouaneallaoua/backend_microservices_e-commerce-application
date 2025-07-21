package com.allaoua.billservice.dto;

import com.allaoua.billservice.enums.BillStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class BillRequestDto {
    private String billNumber;
    private String userId;
    private List<BillProductRequestDto> billProducts;

}
