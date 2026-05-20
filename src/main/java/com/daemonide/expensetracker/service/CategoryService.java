package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }


    public void deleteCategoryById(long id){
        categoryRepository.deleteById(id);
    }

    public Category editCategory(long id,Category newCategory){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Given Id do not exist"));
        category.setName(newCategory.getName());
        return categoryRepository.save(category);
    }
}
