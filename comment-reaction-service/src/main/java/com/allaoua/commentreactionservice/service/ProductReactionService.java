package com.allaoua.commentreactionservice.service;

import com.allaoua.commentreactionservice.dto.CommentDto;
import com.allaoua.commentreactionservice.dto.ProductReactionDto;
import com.allaoua.commentreactionservice.entity.Comment;
import com.allaoua.commentreactionservice.entity.ProductReaction;
import com.allaoua.commentreactionservice.repository.CommentRepository;
import com.allaoua.commentreactionservice.repository.ProductReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductReactionService {
    @Autowired
    private ProductReactionRepository productReactionRepository;

    public ResponseEntity<List<ProductReaction>> getAllProductReactions() {
        return ResponseEntity.ok(productReactionRepository.findAll());
    }

    public ResponseEntity<List<ProductReaction>> getAllProductReactionWithProductId(String productId) {
        return ResponseEntity.ok(productReactionRepository.findByProductId(productId));
    }

    public ResponseEntity<List<ProductReaction>> getAllProductReactionWithUserId(String userId) {
        return ResponseEntity.ok(productReactionRepository.findByUserId(userId));
    }

    public ResponseEntity<ProductReaction> saveProductReaction(ProductReactionDto productReactionDto) {
        ProductReaction productReaction = ProductReaction.builder()
                .productId(productReactionDto.getProductId())
                .userId(productReactionDto.getUserId())
                .build();
        return ResponseEntity.ok(productReactionRepository.save(productReaction));
    }

    public ResponseEntity<String> deleteProductReaction(String productId, String userId) {
        productReactionRepository.deleteByProductIdAndUserId(productId, userId);
        return ResponseEntity.ok("Deleted product reaction");
    }


}
