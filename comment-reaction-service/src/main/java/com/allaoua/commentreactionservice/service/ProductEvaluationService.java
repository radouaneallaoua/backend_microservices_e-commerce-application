package com.allaoua.commentreactionservice.service;

import com.allaoua.commentreactionservice.dto.ProductEvaluationDto;
import com.allaoua.commentreactionservice.entity.ProductEvaluation;
import com.allaoua.commentreactionservice.repository.ProductEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductEvaluationService {
    @Autowired
    private ProductEvaluationRepository productEvaluationRepository;

    public ResponseEntity<List<ProductEvaluation>> getAllProductEvaluations() {
        return ResponseEntity.ok(productEvaluationRepository.findAll());
    }

    public ResponseEntity<List<ProductEvaluation>> getAllProductEvaluationWithProductId(String productId) {
        return ResponseEntity.ok(productEvaluationRepository.findByProductId(productId));
    }

    public ResponseEntity<List<ProductEvaluation>> getAllProductEvaluationWithUserId(String userId) {
        return ResponseEntity.ok(productEvaluationRepository.findByUserId(userId));
    }

    public ResponseEntity<ProductEvaluation> saveProductEvaluation(ProductEvaluationDto productEvaluationDto) {
        ProductEvaluation productEvaluation = ProductEvaluation.builder()
                .productId(productEvaluationDto.getProductId())
                .evaluation(productEvaluationDto.getEvaluation())
                .userId(productEvaluationDto.getUserId())
                .build();
        return ResponseEntity.ok(productEvaluationRepository.save(productEvaluation));
    }

}
