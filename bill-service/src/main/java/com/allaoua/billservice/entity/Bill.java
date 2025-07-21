package com.allaoua.billservice.entity;

import com.allaoua.billservice.dto.BillResponseDto;
import com.allaoua.billservice.enums.BillStatus;
import com.allaoua.billservice.models.UserResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter  @AllArgsConstructor @NoArgsConstructor @Builder
public class Bill {
    @Id
    private String id;
    private Date paymentDate;
    private BillStatus billStatus;
    private String billNumber;
    private String userId;

    @Transient
    private UserResponseDto user;

    @OneToMany(mappedBy = "bill")
    private List<BillProduct> billProducts;

    public BillResponseDto toDto(){
        return BillResponseDto.builder()
                .id(id)
                .billNumber(billNumber)
                .billStatus(billStatus)
                .paymentDate(paymentDate)
                .userId(userId)
                .user(user)
                .billProducts(billProducts.stream().map(BillProduct::toDto).toList())
                .build();
    }


}
