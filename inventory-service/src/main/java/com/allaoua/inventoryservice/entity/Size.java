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

    @ManyToMany()
    @JoinTable( name = "product_sizes",joinColumns = @JoinColumn(name = "size_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public SizeResponseDto toDto() {
        return SizeResponseDto.builder()
                .id(id)
                .label(label)
                .build();
    }

}
