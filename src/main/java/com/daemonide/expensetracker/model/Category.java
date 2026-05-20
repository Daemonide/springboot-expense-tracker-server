package com.daemonide.expensetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Category {
    @Id
    @GeneratedValue
    private long category_id;

    private String name;

    public Category(){}

    public Category(String name){
        this.name = name;
    }
}
