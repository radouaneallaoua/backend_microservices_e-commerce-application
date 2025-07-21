package com.allaoua.commentreactionservice.web;


import com.allaoua.commentreactionservice.dto.ProductEvaluationDto;
import com.allaoua.commentreactionservice.entity.ProductEvaluation;
import com.allaoua.commentreactionservice.service.ProductEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-evaluation")
public class ProductEvaluationController {
    @Autowired
    private ProductEvaluationService productEvaluationService;
    
    @GetMapping("")
    public ResponseEntity<List<ProductEvaluation>> getAllProductEvaluations() {
        return productEvaluationService.getAllProductEvaluations();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductEvaluation>> getAllProductEvaluationsWithProductId(@PathVariable("productId") String productId) {
        return productEvaluationService.getAllProductEvaluationWithProductId(productId);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductEvaluation>> getAllProductEvaluationsWithUserId(@PathVariable("userId") String userId) {
        return productEvaluationService.getAllProductEvaluationWithUserId(userId);
    }
    
    @PostMapping("")
    public ResponseEntity<ProductEvaluation> saveProductEvaluation(@RequestBody ProductEvaluationDto productEvaluationDto) {
        return productEvaluationService.saveProductEvaluation(productEvaluationDto);
    }
}
