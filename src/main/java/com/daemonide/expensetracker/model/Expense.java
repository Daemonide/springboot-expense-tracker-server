package com.daemonide.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @Positive
    private double amount;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
