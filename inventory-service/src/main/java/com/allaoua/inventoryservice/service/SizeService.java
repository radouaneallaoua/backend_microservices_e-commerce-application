package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.SizeRequestDto;
import com.allaoua.inventoryservice.dto.SizeResponseDto;
import com.allaoua.inventoryservice.entity.Size;
import com.allaoua.inventoryservice.exception.CategoryNotFoundException;
import com.allaoua.inventoryservice.exception.SizeNotFoundException;
import com.allaoua.inventoryservice.repository.SizeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SizeService {
   final private SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public ResponseEntity<List<SizeResponseDto>> getAllSizes() {
        return ResponseEntity.ok(sizeRepository.findAll().stream().map(Size::toDto).toList());
    }

    public ResponseEntity<SizeResponseDto> saveSize(SizeRequestDto sizeRequestDto) {
        Size size=Size.builder()
                .label(sizeRequestDto.getLabel())
                .build();
        return ResponseEntity.ok(sizeRepository.save(size).toDto());
    }

    public ResponseEntity<SizeResponseDto> getSizeById(Long sizeId) {
        Size size=sizeRepository.findById(sizeId).orElseThrow(()-> new SizeNotFoundException("size not found with id "+sizeId));
        return ResponseEntity.ok(size.toDto());
    }

    public ResponseEntity<SizeResponseDto> updateSize(Long sizeId, SizeRequestDto sizeRequestDto) {
        Size size=sizeRepository.findById(sizeId).orElse(null);
        if(size==null) {
            throw new SizeNotFoundException("size not found with id "+sizeId);
        }
        size.setLabel(sizeRequestDto.getLabel());
        return ResponseEntity.ok(sizeRepository.save(size).toDto());
    }

    public ResponseEntity<String> deleteSize(Long sizeId) {
       sizeRepository.deleteById(sizeId);
        return ResponseEntity.ok("size successfully deleted");
    }


}
