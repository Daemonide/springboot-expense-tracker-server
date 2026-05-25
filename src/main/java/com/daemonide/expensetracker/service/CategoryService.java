package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.CategoryRequestDTO;
import com.daemonide.expensetracker.dto.CategoryResponseDTO;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.mapper.CategoryMapper;
import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CustomUserDetailsService userDetailsService;

    public List<CategoryResponseDTO> getAllCategory() {
        AppUser currentUser = userDetailsService.getCurrentUser();
        return CategoryMapper.toDTOList(categoryRepository.findByUser(currentUser));
    }

    public CategoryResponseDTO getCategoryById(long id) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        Category category = categoryRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new NoSuchCategoryExistsException("Category not found or unauthorized"));
        return CategoryMapper.toDTO(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        Category category = CategoryMapper.toEntity(dto);
        category.setUser(currentUser);
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }


    public void deleteCategoryById(long id) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        Category category = categoryRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new NoSuchCategoryExistsException("Category not found or unauthorized"));
        categoryRepository.delete(category);
    }

    public CategoryResponseDTO editCategory(long id, CategoryRequestDTO newCategory) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        Category category = categoryRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new NoSuchCategoryExistsException("Category not found or unauthorized"));
        category.setName(newCategory.getName());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }
}
