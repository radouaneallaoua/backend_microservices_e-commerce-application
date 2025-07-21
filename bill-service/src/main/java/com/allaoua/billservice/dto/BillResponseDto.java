package com.allaoua.billservice.dto;

import com.allaoua.billservice.enums.BillStatus;
import com.allaoua.billservice.models.UserResponseDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder  @Getter
@Setter
public class BillResponseDto {
    private String id;
    private Date paymentDate;
    private BillStatus billStatus;
    private String billNumber;
    private String userId;
    private List<BillProductResponseDto> billProducts;
    private UserResponseDto user;

}
