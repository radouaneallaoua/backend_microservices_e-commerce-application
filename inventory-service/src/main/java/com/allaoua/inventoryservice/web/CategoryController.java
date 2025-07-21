package com.allaoua.inventoryservice.web;


import com.allaoua.inventoryservice.dto.CategoryRequestDto;
import com.allaoua.inventoryservice.dto.CategoryResponseDto;
import com.allaoua.inventoryservice.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryResponseDto> saveCategory(@ModelAttribute CategoryRequestDto categoryRequestDto) throws IOException {
        return categoryService.saveCategory(categoryRequestDto);
    }
    
    @PutMapping(value="/{categoryId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long categoryId,
                                                              @RequestParam(name = "name",required = false) String name,
                                                              @RequestParam(name = "description",required = false) String description,
                                                              @RequestParam(name = "imageURL",required = false) MultipartFile imageURL,
                                                              @RequestParam(name = "parentCategoryId",required = false) Long parentCategoryId
                                                              ) throws IOException {
        return categoryService.updateCategory(categoryId ,name,description,imageURL,parentCategoryId);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping(value = "{categoryId}/image",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getCategoryImage(@PathVariable Long categoryId) throws IOException {
        return categoryService.getCategoryImage(categoryId);
    }
    
}
