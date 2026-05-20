package com.daemonide.expensetracker.service;

import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;
import com.daemonide.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    private UrlPathHelper urlPathHelper;

    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(long id){
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
    }

    public List<Expense> getAllExpense(){
        return expenseRepository.findAll();
    }

    public List<Expense> getExpenseByCategory(Category category){
        return expenseRepository.findByCategory(category);
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

    public Expense editExpense(long id,Expense updatedExpense){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));
        expense.setTitle(updatedExpense.getTitle());
        expense.setAmount(updatedExpense.getAmount());
        expense.setDate(updatedExpense.getDate());
        expense.setCategory(updatedExpense.getCategory());
        return expenseRepository.save(expense);
    }
}
