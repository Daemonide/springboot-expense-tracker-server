package com.daemonide.expensetracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private double amount;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    public Expense(){}

    public Expense(String title,double amount,LocalDate date,Category category){
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }
}
