package com.allaoua.commentreactionservice.web;


import com.allaoua.commentreactionservice.dto.CommentDto;
import com.allaoua.commentreactionservice.dto.ProductReactionDto;
import com.allaoua.commentreactionservice.entity.Comment;
import com.allaoua.commentreactionservice.entity.ProductReaction;
import com.allaoua.commentreactionservice.service.ProductReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-reaction")
public class ProductReactionController {
    @Autowired
    private ProductReactionService productReactionService;

    @GetMapping("")
    public ResponseEntity<List<ProductReaction>> getAllProductReactions() {
        return productReactionService.getAllProductReactions();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReaction>> getAllProductReactionsWithProductId(@PathVariable("productId") String productId) {
        return productReactionService.getAllProductReactionWithProductId(productId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductReaction>> getAllProductReactionsWithUserId(@PathVariable("userId") String userId) {
        return productReactionService.getAllProductReactionWithUserId(userId);
    }

    @PostMapping("")
    public ResponseEntity<ProductReaction> saveProductReaction(@RequestBody ProductReactionDto productReactionDto) {
        return productReactionService.saveProductReaction(productReactionDto);
    }
}
