package com.allaoua.commentreactionservice.repository;

import com.allaoua.commentreactionservice.entity.ProductEvaluation;
import com.allaoua.commentreactionservice.entity.ProductReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEvaluationRepository extends JpaRepository<ProductEvaluation, Long> {
    List<ProductEvaluation> findByProductId(String productId);
    List<ProductEvaluation> findByUserId(String userId);
}
