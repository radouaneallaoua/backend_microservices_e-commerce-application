package com.allaoua.commentreactionservice.service;

import com.allaoua.commentreactionservice.dto.CommentDto;
import com.allaoua.commentreactionservice.entity.Comment;
import com.allaoua.commentreactionservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentRepository.findAll());
    }

    public ResponseEntity<Comment> getCommentById(Long id) {
        return ResponseEntity.ok(commentRepository.findById(id).get());
    }


    public ResponseEntity<List<Comment>> getAllCommentWithProductId(String productId) {
        return ResponseEntity.ok(commentRepository.findByProductId(productId));
    }

    public ResponseEntity<Comment> saveComment(CommentDto commentDto) {
        Comment comment= Comment.builder()
                .content(commentDto.getContent())
                .productId(commentDto.getProductId())
                .date(new Date())
                .userId(commentDto.getUserId())
                .build();
        return ResponseEntity.ok(commentRepository.save(comment));
    }

}
