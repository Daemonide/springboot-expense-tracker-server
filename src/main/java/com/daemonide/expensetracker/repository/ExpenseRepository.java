package com.daemonide.expensetracker.repository;

import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(Category category);

    List<Expense> findByUser(AppUser user);

    Optional<Expense> findByIdAndUser(Long id, AppUser user);

    List<Expense> findByCategoryAndUser(Category category, AppUser user);
}
