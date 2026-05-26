package com.daemonide.expensetracker.repository;

import com.daemonide.expensetracker.model.AppUser;
import com.daemonide.expensetracker.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByUser(AppUser user, Pageable pageable);

    Optional<Category> findByIdAndUser(Long id, AppUser user);

    @Query("""
                SELECT c FROM Category c
                WHERE c.user = :user
                AND (
                    COALESCE(:search, '') = ''
                    OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))
                )
            """)
    Page<Category> findByUserAndSearch(
            @Param("user") AppUser user,
            @Param("search") String search,
            Pageable pageable
    );
}