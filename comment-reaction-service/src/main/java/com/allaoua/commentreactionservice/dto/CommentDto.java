package com.allaoua.commentreactionservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class CommentDto {
    private String content;
    private String userId;
    private String productId;
}
