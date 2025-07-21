package com.allaoua.commentreactionservice.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEvaluation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int evaluation;
    private String productId;
    private String userId;


}
