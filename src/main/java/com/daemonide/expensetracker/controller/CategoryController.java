package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getCategory(){
        return categoryService.getAllCategory();
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category editById(@PathVariable long id,@RequestBody Category updatedCategory){
        return categoryService.editCategory(id,updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        categoryService.deleteCategoryById(id);
    }
}
