package com.allaoua.inventoryservice.service;

import com.allaoua.inventoryservice.dto.CategoryRequestDto;
import com.allaoua.inventoryservice.dto.CategoryResponseDto;
import com.allaoua.inventoryservice.entity.Category;
import com.allaoua.inventoryservice.exception.CategoryNotFoundException;
import com.allaoua.inventoryservice.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryService {
   final private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll().stream().map(Category::toDto).toList());
    }

    public ResponseEntity<List<CategoryResponseDto>> getAllCategoriesWithParentCategoryId(Long parentCategoryId) {
        return ResponseEntity.ok(categoryRepository.findByParentCategoryId(parentCategoryId).stream().map(Category::toDto).toList());
    }

    public ResponseEntity<CategoryResponseDto> saveCategory(CategoryRequestDto categoryRequestDto) throws IOException {
        Path path= Paths.get(System.getProperty("user.home"),"e-comm-final","categories_images");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String imageId= UUID.randomUUID().toString();
        Path imagePath=Paths.get(System.getProperty("user.home"),"e-comm-final","categories_images",imageId+".webp");
        Files.copy(categoryRequestDto.getImageURL().getInputStream(),imagePath);
        Category category=Category.builder()
                .name(categoryRequestDto.getName())
                .description(categoryRequestDto.getDescription())
                .imageURL(imagePath.toUri().toString())
                .parentCategory(categoryRequestDto.getParentCategoryId()!=null?categoryRepository.findById(categoryRequestDto.getParentCategoryId()).orElseThrow(()-> new CategoryNotFoundException("category not found with id "+categoryRequestDto.getParentCategoryId())):null)
                .build();
        return ResponseEntity.ok(categoryRepository.save(category).toDto());
    }

    public ResponseEntity<CategoryResponseDto> getCategoryById(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("category not found with id "+categoryId));
        return ResponseEntity.ok(category.toDto());
    }

    public ResponseEntity<CategoryResponseDto> updateCategory(Long categoryId, String name, String description, MultipartFile image,Long parentCategoryId) throws IOException {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if(category==null) {
            throw new CategoryNotFoundException("category not found with id "+categoryId);
        }
        if(name!=null) {
            category.setName(name);
        }
        if(description!=null) {
            category.setDescription(description);
        }
        if(image!=null) {

            Files.delete(Path.of(URI.create(category.getImageURL())));
            Path path=Paths.get(System.getProperty("user.home"),"e-comm-final","categories_images");
            String imageId= UUID.randomUUID().toString();
            Path newImagePath=Paths.get(System.getProperty("user.home"),"e-comm-final","categories_images",imageId+".webp");
            Files.copy(image.getInputStream(),newImagePath);
            category.setImageURL(newImagePath.toUri().toString());

        }
        if(parentCategoryId!=null) {
            Category parentCategory=categoryRepository.findById(parentCategoryId).orElse(null);
            if(parentCategory!=null) {
                category.setParentCategory(parentCategory);
            }
            throw new CategoryNotFoundException("parent category not found with id "+parentCategoryId);
        }

        return ResponseEntity.ok(categoryRepository.save(category).toDto());
    }

    public ResponseEntity<String> deleteCategory(Long categoryId) {
       categoryRepository.deleteById(categoryId);
        return ResponseEntity.ok("category successfully deleted");
    }


    public ResponseEntity<byte[]> getCategoryImage(Long categoryId) throws IOException {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if(category==null) {
            throw new CategoryNotFoundException("category not found with id "+categoryId);
        }
        return ResponseEntity.ok(Files.readAllBytes(Path.of(URI.create(category.getImageURL()))));
    }
}
