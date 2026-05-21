package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.ExpenseRequestDTO;
import com.daemonide.expensetracker.dto.ExpenseResponseDTO;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.exception.NoSuchExpenseExistsException;
import com.daemonide.expensetracker.mapper.ExpenseMapper;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;
import com.daemonide.expensetracker.repository.CategoryRepository;
import com.daemonide.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseResponseDTO addExpense(ExpenseRequestDTO expense){
        Category category = categoryRepository.findById(expense.getCategoryId())
                .orElseThrow(() -> new NoSuchCategoryExistsException("No Category exists with the specified ID"));
        return ExpenseMapper.toDTO(expenseRepository.save(ExpenseMapper.toEntity(expense,category)));
    }

    public ExpenseResponseDTO getExpenseById(long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new NoSuchExpenseExistsException("No Expense exists with the specified ID"));
        return ExpenseMapper.toDTO(expense);
    }

    public List<ExpenseResponseDTO> getAllExpense(){
        return ExpenseMapper.toDTOList(expenseRepository.findAll());
    }

    public List<ExpenseResponseDTO> getExpenseByCategory(Category category){
        return ExpenseMapper.toDTOList(expenseRepository.findByCategory(category));
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

    public ExpenseResponseDTO editExpense(long id,ExpenseRequestDTO updatedExpense){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new NoSuchExpenseExistsException("No Expense exists with the specified ID"));
        expense.setTitle(updatedExpense.getTitle());
        expense.setAmount(updatedExpense.getAmount());
        expense.setDate(updatedExpense.getDate());
        Category category = categoryRepository.findById(updatedExpense.getCategoryId())
                        .orElseThrow(() -> new NoSuchCategoryExistsException("No Category exists with the specified ID"));
        expense.setCategory(category);
        return  ExpenseMapper.toDTO(expenseRepository.save(expense));
    }
}
