package com.daemonide.expensetracker.mapper;

import com.daemonide.expensetracker.dto.ExpenseRequestDTO;
import com.daemonide.expensetracker.dto.ExpenseResponseDTO;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;

import java.util.List;

public class ExpenseMapper {
    public static Expense toEntity(ExpenseRequestDTO dto, Category category) {
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setCategory(category);
        return expense;
    }

    public static ExpenseResponseDTO toDTO(Expense expense) {
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setExpenseID(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setCategoryId(expense.getCategory().getCategory_id());
        dto.setCategoryName(expense.getCategory().getName());
        return dto;
    }

    public static List<ExpenseResponseDTO> toDTOList(List<Expense> expenses) {
        return expenses.stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }
}
