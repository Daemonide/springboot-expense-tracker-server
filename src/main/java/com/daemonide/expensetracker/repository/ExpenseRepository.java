package com.daemonide.expensetracker.repository;

import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(Category category);
}
