package com.daemonide.expensetracker.repository;

import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.model.Category;
import com.daemonide.expensetracker.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long>,
        JpaSpecificationExecutor<Expense> {
    @Query("SELECT e FROM Expense e JOIN e.category c WHERE e.user = :user")
    Page<Expense> findByUser(@Param("user") AppUser user, Pageable pageable);

    @Query("SELECT e FROM Expense e JOIN e.category c WHERE e.category = :category AND e.user = :user")
    Page<Expense> findByCategoryAndUser(
            @Param("category") Category category,
            @Param("user") AppUser user,
            Pageable pageable
    );

    Optional<Expense> findByIdAndUser(Long id, AppUser user);

    @Query("""
                SELECT e FROM Expense e
                JOIN e.category c
                WHERE e.user = :user
                AND (
                    coalesce(:search, '') = ''
                    OR lower(e.title) LIKE lower(concat('%', :search, '%'))
                )
            """)
    Page<Expense> findByUserAndSearch(
            @Param("user") AppUser user,
            @Param("search") String search,
            Pageable pageable
    );
}