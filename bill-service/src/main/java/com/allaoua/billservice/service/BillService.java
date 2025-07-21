package com.allaoua.billservice.service;

import com.allaoua.billservice.clients.UserRestClient;
import com.allaoua.billservice.dto.BillProductResponseDto;
import com.allaoua.billservice.dto.BillRequestDto;
import com.allaoua.billservice.dto.BillResponseDto;
import com.allaoua.billservice.entity.Bill;
import com.allaoua.billservice.entity.BillProduct;
import com.allaoua.billservice.enums.BillStatus;
import com.allaoua.billservice.exception.BillNotFoundException;
import com.allaoua.billservice.repository.BillProductRepository;
import com.allaoua.billservice.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BillService {
    final private BillRepository billRepository;
    private final UserRestClient userRestClient;
    final  private BillProductRepository productRepository;
    private final BillProductRepository billProductRepository;

    public BillService(BillRepository billRepository, UserRestClient userRestClient, BillProductRepository productRepository, BillProductRepository billProductRepository) {
        this.billRepository = billRepository;
        this.userRestClient = userRestClient;
        this.productRepository = productRepository;
        this.billProductRepository = billProductRepository;
    }

    public ResponseEntity<List<BillResponseDto>> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        bills.forEach(b->{
            b.setUser(userRestClient.getUser(b.getUserId()).getBody());
        });
        return ResponseEntity.ok(bills.stream().map(Bill::toDto).toList());
    }

    public ResponseEntity<List<BillResponseDto>> getAllBillsByUserId(String userId) {
        List<Bill> bills = billRepository.findByUserId(userId);
        bills.forEach(b->{
            b.setUser(userRestClient.getUser(b.getUserId()).getBody());
        });
        return ResponseEntity.ok(bills.stream().map(Bill::toDto).toList());

    }

    public ResponseEntity<BillResponseDto> getBillById(String billId) {
        Bill bill=billRepository.findById(billId).orElseThrow(()-> new BillNotFoundException("bill not found with id "+billId));
        bill.setUser(userRestClient.getUser(bill.getUserId()).getBody());
        return ResponseEntity.ok(bill.toDto());
    }

    public ResponseEntity<BillResponseDto> saveBill(BillRequestDto billRequestDto) {

        Bill bill=Bill.builder()
                .billNumber(billRequestDto.getBillNumber())
                .billStatus(BillStatus.PENDING)
                .paymentDate(new Date())
                .userId(billRequestDto.getUserId())
                .user(userRestClient.getUser(billRequestDto.getUserId()).getBody())
                .id(UUID.randomUUID().toString())
                .build();
        Bill savedBill=billRepository.save(bill);
        List<BillProduct> billProductList=new ArrayList<>();
        billRequestDto.getBillProducts().forEach(bp->{
            BillProduct billProduct=BillProduct.builder()
                    .bill(savedBill)
                    .productId(bp.getProductId())
                    .quantity(bp.getQuantity())
                    .sizeId(bp.getSizeId())
                    .colorId(bp.getColorId())
                    .build();
            BillProduct savedBillProduct= billProductRepository.save(billProduct);
            billProductList.add(savedBillProduct);
        });
        savedBill.setBillProducts(billProductList);
        savedBill.setUser(userRestClient.getUser(savedBill.getUserId()).getBody());
        return ResponseEntity.ok(billRepository.save(savedBill).toDto());
    }



    public ResponseEntity<String> deleteBillById(String billId) {
        Bill bill=billRepository.findById(billId).orElseThrow(()-> new BillNotFoundException("bill not found with id "+billId));
        billProductRepository.deleteAll(bill.getBillProducts());
        billRepository.delete(bill);
        return ResponseEntity.ok("bill successfully deleted with id "+billId);
    }

    public ResponseEntity<List<BillResponseDto>> getAllBillsByUserIdAndBillStatus(String userId, BillStatus billStatus) {
        List<Bill> bills = billRepository.findByUserIdAndBillStatus(userId,billStatus);
        bills.forEach(b->{
            b.setUser(userRestClient.getUser(b.getUserId()).getBody());
        });
        return ResponseEntity.ok(bills.stream().map(Bill::toDto).toList());
    }

    public ResponseEntity<List<BillResponseDto>> getAllBillsByBillStatusWithPage(BillStatus billStatus,int pageNumber,int pageSize) {
        List<Bill> bills = billRepository.findByBillStatusOrderByPaymentDateDesc(billStatus, Pageable.ofSize(pageSize).withPage(pageNumber));

        bills.forEach(b->{
            b.setUser(userRestClient.getUser(b.getUserId()).getBody());
        });
        return ResponseEntity.ok(bills.stream().map(Bill::toDto).toList());
    }

    public ResponseEntity<List<BillProductResponseDto>> getAllBillProductsByBillId(String billId) {
        return ResponseEntity.ok(billProductRepository.findByBillId(billId).stream().map(BillProduct::toDto).toList());
    }



}
