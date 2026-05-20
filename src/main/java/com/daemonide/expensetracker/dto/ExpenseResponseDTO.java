package com.daemonide.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@JsonPropertyOrder({
        "expenseID",
        "title",
        "amount",
        "date",
        "categoryId",
        "categoryName"
})
public class ExpenseResponseDTO {
    private long expenseID;
    private String title;
    private double amount;
    private LocalDate date;
    private long categoryId;
    private String categoryName;
}
