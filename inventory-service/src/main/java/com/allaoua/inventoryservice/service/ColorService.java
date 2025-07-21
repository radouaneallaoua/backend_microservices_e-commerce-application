package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.ColorRequestDto;
import com.allaoua.inventoryservice.dto.ColorResponseDto;
import com.allaoua.inventoryservice.entity.Color;
import com.allaoua.inventoryservice.exception.CategoryNotFoundException;
import com.allaoua.inventoryservice.exception.ColorNotFoundException;
import com.allaoua.inventoryservice.repository.ColorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ColorService {
   final private ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public ResponseEntity<List<ColorResponseDto>> getAllColors() {
        return ResponseEntity.ok(colorRepository.findAll().stream().map(Color::toDto).toList());
    }

    public ResponseEntity<ColorResponseDto> saveColor(ColorRequestDto colorRequestDto) {
        Color color=Color.builder()
                .color(colorRequestDto.getColor())
                .build();
        return ResponseEntity.ok(colorRepository.save(color).toDto());
    }

    public ResponseEntity<ColorResponseDto> getColorById(Long colorId) {
        Color color=colorRepository.findById(colorId).orElseThrow(()-> new ColorNotFoundException("color not found with id "+colorId));
        return ResponseEntity.ok(color.toDto());
    }

    public ResponseEntity<ColorResponseDto> updateColor(Long colorId, ColorRequestDto colorRequestDto) {
        Color color=colorRepository.findById(colorId).orElse(null);
        if(color==null) {
            throw new ColorNotFoundException("color not found with id "+colorId);
        }
        return ResponseEntity.ok(colorRepository.save(color).toDto());
    }

    public ResponseEntity<String> deleteColor(Long colorId) {
       colorRepository.deleteById(colorId);
        return ResponseEntity.ok("color successfully deleted");
    }


}
