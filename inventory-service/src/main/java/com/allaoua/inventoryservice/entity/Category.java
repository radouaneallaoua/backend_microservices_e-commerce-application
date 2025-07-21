package com.allaoua.inventoryservice.entity;


import com.allaoua.inventoryservice.dto.CategoryResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageURL;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;

    @ManyToOne
    private Category parentCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;

    public CategoryResponseDto toDto(){
        return  CategoryResponseDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .imageURL(imageURL)
                .parentCategoryId(parentCategory!=null?parentCategory.getId():null)
                .build();
    }
}
