package com.learncode.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learncode.blog.payloads.ApiResponse;
import com.learncode.blog.payloads.CategoryDto;
import com.learncode.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    
    // POST - Create Category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }

    // PUT - Update Category
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CategoryDto categoryDto,
            @PathVariable("catId") int catId // ✅ match path variable name
    ) {
        CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    
    // DELETE - Delete Category
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable("catId") Integer catId) {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    
    // GET - Get Single Category
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable("catId") Integer catId) {
        CategoryDto categoryDto = this.categoryService.getCategory(catId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    
    // GET - Get All Categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categories = this.categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
}