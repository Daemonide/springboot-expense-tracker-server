package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;
import com.daemonide.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getExpense(){
        return expenseService.getAllExpense();
    }

    @GetMapping("/category/{category}")
    public List<Expense> getByCategory(@PathVariable Category category){
        return expenseService.getExpenseByCategory(category);
    }

    @GetMapping("/{id}")
    public Expense getById(@PathVariable long id){
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public Expense editExpense(@PathVariable long id,@RequestBody Expense updatedExpense){
        return expenseService.editExpense(id,updatedExpense);
    }
}
