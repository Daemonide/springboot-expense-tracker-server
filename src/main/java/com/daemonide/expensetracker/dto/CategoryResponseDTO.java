package com.daemonide.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "categoryId",
        "name",
        "expenseCount"
})
public class CategoryResponseDTO {
    long categoryId;
    String name;
    private Long expenseCount;
}
