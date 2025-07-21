package com.allaoua.billservice.repository;

import com.allaoua.billservice.entity.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillProductRepository extends JpaRepository<BillProduct, Long> {
    List<BillProduct> findByBillId(String billId);

}
