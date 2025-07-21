package com.allaoua.billservice.repository;

import com.allaoua.billservice.entity.Bill;
import com.allaoua.billservice.enums.BillStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    List<Bill> findByUserId(String userId);
    List<Bill> findByUserIdAndBillStatus(String userId, BillStatus billStatus);
    List<Bill> findByBillStatusOrderByPaymentDateDesc(BillStatus billStatus, Pageable pageable);
}
