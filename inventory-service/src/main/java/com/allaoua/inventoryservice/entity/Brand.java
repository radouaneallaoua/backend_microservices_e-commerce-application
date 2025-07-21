package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.BrandRequestDto;
import com.allaoua.inventoryservice.dto.BrandResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> products;

    public BrandResponseDto toDto(){
        return  BrandResponseDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}
