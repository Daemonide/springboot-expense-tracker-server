package com.daemonide.expensetracker.pagination;

import lombok.Data;

import java.util.Collection;

@Data
public class PagingResult<T> {

    private Collection<T> content;

    private Integer totalPages;

    private long totalElements;

    private Integer size;

    private Integer page;

    private boolean empty;

    private String sortField;

    private String sortDirection;

    public PagingResult(
            Collection<T> content,
            Integer totalPages,
            long totalElements,
            Integer size,
            Integer page,
            boolean empty,
            String sortField,
            String sortDirection
    ) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.page = page + 1;
        this.empty = empty;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
    }
}