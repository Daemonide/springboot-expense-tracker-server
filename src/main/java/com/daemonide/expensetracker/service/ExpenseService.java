package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.dto.ExpenseRequestDTO;
import com.daemonide.expensetracker.dto.ExpenseResponseDTO;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.exception.NoSuchExpenseExistsException;
import com.daemonide.expensetracker.mapper.ExpenseMapper;
import com.daemonide.expensetracker.model.AppUser;
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
    private final CustomUserDetailsService userDetailsService;

    public ExpenseResponseDTO addExpense(ExpenseRequestDTO expenseDto) {
        AppUser currentUser = userDetailsService.getCurrentUser();


        Category category = categoryRepository.findByIdAndUser(expenseDto.getCategoryId(), currentUser)
                .orElseThrow(() -> new NoSuchCategoryExistsException("Category not found or unauthorized"));

        Expense expense = ExpenseMapper.toEntity(expenseDto, category);
        expense.setUser(currentUser);

        return ExpenseMapper.toDTO(expenseRepository.save(expense));
    }

    public ExpenseResponseDTO getExpenseById(long id) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        Expense expense = expenseRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new NoSuchExpenseExistsException("Expense not found or unauthorized"));
        return ExpenseMapper.toDTO(expense);
    }

    public List<ExpenseResponseDTO> getAllExpense() {
        AppUser currentUser = userDetailsService.getCurrentUser();
        return ExpenseMapper.toDTOList(expenseRepository.findByUser(currentUser));
    }

    public List<ExpenseResponseDTO> getExpenseByCategory(Category category) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        return ExpenseMapper.toDTOList(expenseRepository.findByCategoryAndUser(category, currentUser));
    }

    public void deleteExpense(Long id) {
        AppUser currentUser = userDetailsService.getCurrentUser();
        Expense expense = expenseRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new NoSuchExpenseExistsException("Expense not found or unauthorized"));
        expenseRepository.delete(expense);
    }

    public ExpenseResponseDTO editExpense(long id, ExpenseRequestDTO updatedExpense) {
        AppUser currentUser = userDetailsService.getCurrentUser();

        Expense expense = expenseRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new NoSuchExpenseExistsException("Expense not found or unauthorized"));

        Category category = categoryRepository.findByIdAndUser(updatedExpense.getCategoryId(), currentUser)
                .orElseThrow(() -> new NoSuchCategoryExistsException("Category not found or unauthorized"));

        expense.setTitle(updatedExpense.getTitle());
        expense.setAmount(updatedExpense.getAmount());
        expense.setDate(updatedExpense.getDate());
        expense.setCategory(category);
        expense.setStatus(updatedExpense.getStatus());

        return ExpenseMapper.toDTO(expenseRepository.save(expense));
    }
}
