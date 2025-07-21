package com.allaoua.billservice.entity;


import com.allaoua.billservice.dto.BillProductResponseDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private int quantity;
    private Long sizeId;
    private Long colorId;

    @ManyToOne
    private Bill bill;

    public BillProductResponseDto toDto() {
        return BillProductResponseDto.builder()
                .id(id)
                .billId(bill!=null?bill.getId():null)
                .productId(productId)
                .quantity(quantity)
                .sizeId(sizeId)
                .colorId(colorId)
                .build();
    }
}
