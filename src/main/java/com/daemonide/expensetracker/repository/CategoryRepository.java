package com.daemonide.expensetracker.repository;

import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(AppUser user);

    Optional<Category> findByIdAndUser(Long id, AppUser user);
}
