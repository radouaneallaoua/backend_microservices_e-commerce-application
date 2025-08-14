package com.allaoua.inventoryservice.web;


import com.allaoua.inventoryservice.dto.SizeRequestDto;
import com.allaoua.inventoryservice.dto.SizeResponseDto;
import com.allaoua.inventoryservice.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sizes")
@CrossOrigin

public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("")
    public ResponseEntity<List<SizeResponseDto>> getAllSizes() {
        return sizeService.getAllSizes();
    }

    @PostMapping("")
    public ResponseEntity<SizeResponseDto> saveSize(@RequestBody(required = true) SizeRequestDto sizeRequestDto) {
        return sizeService.saveSize(sizeRequestDto);
    }

    @PutMapping("/{sizeId}")
    public ResponseEntity<SizeResponseDto> updateSize(@PathVariable Long sizeId, @RequestBody(required = true) SizeRequestDto sizeRequestDto) {
        return sizeService.updateSize(sizeId ,sizeRequestDto);
    }

    @DeleteMapping("/{sizeId}")
    public ResponseEntity<String> deleteSize(@PathVariable Long sizeId) {
        return sizeService.deleteSize(sizeId);
    }

}
