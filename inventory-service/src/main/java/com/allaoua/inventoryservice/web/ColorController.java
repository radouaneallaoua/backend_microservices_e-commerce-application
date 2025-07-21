package com.allaoua.inventoryservice.web;


import com.allaoua.inventoryservice.dto.ColorRequestDto;
import com.allaoua.inventoryservice.dto.ColorResponseDto;
import com.allaoua.inventoryservice.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("")
    public ResponseEntity<List<ColorResponseDto>> getAllColors() {
        return colorService.getAllColors();
    }

    @PostMapping("")
    public ResponseEntity<ColorResponseDto> saveColor(@RequestBody(required = true) ColorRequestDto colorRequestDto) {
        return colorService.saveColor(colorRequestDto);
    }

    @PutMapping("/{colorId}")
    public ResponseEntity<ColorResponseDto> updateColor(@PathVariable Long colorId, @RequestBody(required = true) ColorRequestDto colorRequestDto) {
        return colorService.updateColor(colorId ,colorRequestDto);
    }
    @DeleteMapping("/{colorId}")
    public ResponseEntity<String> deleteColor(@PathVariable Long colorId) {
        return colorService.deleteColor(colorId);
    }

}
