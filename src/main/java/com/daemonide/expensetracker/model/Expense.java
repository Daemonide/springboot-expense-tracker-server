package com.daemonide.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
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
