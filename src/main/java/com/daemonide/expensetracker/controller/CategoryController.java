package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.dto.CategoryRequestDTO;
import com.daemonide.expensetracker.dto.CategoryResponseDTO;
import com.daemonide.expensetracker.exception.ErrorResponse;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.pagination.PaginationRequest;
import com.daemonide.expensetracker.pagination.PagingResult;
import com.daemonide.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public PagingResult<CategoryResponseDTO> getCategory(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "false") Boolean fetchAll
    ) {

        PaginationRequest request = PaginationRequest.builder()
                .page(page)
                .size(size)
                .sortField(sortField)
                .direction(Sort.Direction.valueOf(direction.toUpperCase()))
                .fetchAll(fetchAll)
                .build();

        return categoryService.getAllCategory(request);
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
