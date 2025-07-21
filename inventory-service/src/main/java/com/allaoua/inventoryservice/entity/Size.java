package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.SizeRequestDto;
import com.allaoua.inventoryservice.dto.SizeResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Size {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

    @OneToMany(mappedBy = "size")
    private List<ProductSize> productSizes;

    public SizeResponseDto toDto() {
        return SizeResponseDto.builder()
                .id(id)
                .label(label)
                .build();
    }

}
