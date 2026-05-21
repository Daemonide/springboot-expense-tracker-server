package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.dto.ExpenseRequestDTO;
import com.daemonide.expensetracker.dto.ExpenseResponseDTO;
import com.daemonide.expensetracker.exception.ErrorResponse;
import com.daemonide.expensetracker.exception.NoSuchExpenseExistsException;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseResponseDTO createExpense(@Valid @RequestBody ExpenseRequestDTO expense) {
        return expenseService.addExpense(expense);
    }

    @GetMapping
    public List<ExpenseResponseDTO> getExpense() {
        return expenseService.getAllExpense();
    }

    @GetMapping("/category/{category}")
    public List<ExpenseResponseDTO> getByCategory(@PathVariable Category category) {
        return expenseService.getExpenseByCategory(category);
    }

    @GetMapping("/{id}")
    public ExpenseResponseDTO getById(@PathVariable long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public ExpenseResponseDTO editExpense(@PathVariable long id, @Valid @RequestBody ExpenseRequestDTO updatedExpense) {
        return expenseService.editExpense(id, updatedExpense);
    }

    @ExceptionHandler(value = NoSuchExpenseExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchExpenseExistsException(NoSuchExpenseExistsException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
