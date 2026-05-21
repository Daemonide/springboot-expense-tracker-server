package com.daemonide.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonPropertyOrder({
        "title",
        "amount",
        "date",
        "categoryId",
})
public class ExpenseRequestDTO {
    private String title;
    private double amount;
    private LocalDate date;
    private long categoryId;
}
