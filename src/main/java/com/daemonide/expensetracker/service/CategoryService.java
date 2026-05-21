package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.CategoryRequestDTO;
import com.daemonide.expensetracker.dto.CategoryResponseDTO;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.mapper.CategoryMapper;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getAllCategory() {
        return CategoryMapper.toDTOList(categoryRepository.findAll());
    }

    public CategoryResponseDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryExistsException("No Category exists with the specified ID"));
        return CategoryMapper.toDTO(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO category) {
        return CategoryMapper.toDTO(categoryRepository.save(CategoryMapper.toEntity(category)));
    }


    public void deleteCategoryById(long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO editCategory(long id, CategoryRequestDTO newCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryExistsException("No Category exists with the specified ID"));
        category.setName(newCategory.getName());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }
}
