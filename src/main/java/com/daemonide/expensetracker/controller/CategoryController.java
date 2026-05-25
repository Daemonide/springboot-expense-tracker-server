package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.dto.CategoryRequestDTO;
import com.daemonide.expensetracker.dto.CategoryResponseDTO;
import com.daemonide.expensetracker.exception.ErrorResponse;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDTO> getCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping
    public CategoryResponseDTO addCategory(@Valid @RequestBody CategoryRequestDTO category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO editById(@PathVariable long id, @Valid @RequestBody CategoryRequestDTO updatedCategory) {
        return categoryService.editCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        categoryService.deleteCategoryById(id);
    }

    @ExceptionHandler(value = NoSuchCategoryExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchCategoryExistsException(NoSuchCategoryExistsException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
