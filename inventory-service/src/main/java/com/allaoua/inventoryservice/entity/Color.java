package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.ColorResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Color {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;

    @ManyToMany()
    @JoinTable( name = "product_colors",joinColumns = @JoinColumn(name = "color_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public ColorResponseDto toDto() {
        return ColorResponseDto.builder()
                .id(id)
                .color(color)
                .build();
    }
}
