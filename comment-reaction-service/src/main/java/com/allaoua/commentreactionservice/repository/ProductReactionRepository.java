package com.allaoua.commentreactionservice.repository;

import com.allaoua.commentreactionservice.entity.ProductReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductReactionRepository extends JpaRepository<ProductReaction, Long> {
    List<ProductReaction> findByProductId(String productId);
    void deleteByProductIdAndUserId(String productId, String userId);
    List<ProductReaction> findByUserId(String userId);
}
