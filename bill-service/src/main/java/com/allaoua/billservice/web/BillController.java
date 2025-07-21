package com.allaoua.billservice.web;


import com.allaoua.billservice.dto.BillProductResponseDto;
import com.allaoua.billservice.dto.BillRequestDto;
import com.allaoua.billservice.dto.BillResponseDto;
import com.allaoua.billservice.enums.BillStatus;
import com.allaoua.billservice.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {
    final private  BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("")
    public ResponseEntity<List<BillResponseDto>> getBills() {
        return billService.getAllBills();
    }

    @PostMapping("")
    public ResponseEntity<BillResponseDto> saveBill(@RequestBody BillRequestDto billRequestDto) {
        return billService.saveBill(billRequestDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BillResponseDto>> getBillsByUserId(@PathVariable String userId) {
        return billService.getAllBillsByUserId(userId);
    }

    @GetMapping("/user/{userId}/with-status")
    public ResponseEntity<List<BillResponseDto>> getAllBillsByUserIdAndBillStatus(@PathVariable String userId, @RequestParam BillStatus billStatus) {
        return billService.getAllBillsByUserIdAndBillStatus(userId,billStatus);
    }

    @GetMapping("/with-status")
    public ResponseEntity<List<BillResponseDto>> getAllBillsByBillStatusWithPage( @RequestParam BillStatus billStatus,@RequestParam int pageNumber,@RequestParam int pageSize) {
        return billService.getAllBillsByBillStatusWithPage(billStatus,pageNumber,pageSize);
    }

    @GetMapping("/{billId}/bill-products")
    public ResponseEntity<List<BillProductResponseDto>> getAllBillProductsByBillId(@PathVariable String billId) {
        return billService.getAllBillProductsByBillId(billId);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillResponseDto> getBillById(@PathVariable String billId) {
        return billService.getBillById(billId);
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<String> deleteBillById(@PathVariable String billId) {
        return billService.deleteBillById(billId);
    }

}
