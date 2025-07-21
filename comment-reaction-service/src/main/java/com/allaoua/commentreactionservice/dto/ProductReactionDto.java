package com.allaoua.commentreactionservice.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@Builder
public class ProductReactionDto {
    private String productId;
    private String userId;
}
