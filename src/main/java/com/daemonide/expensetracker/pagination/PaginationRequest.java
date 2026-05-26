package com.daemonide.expensetracker.pagination;

import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRequest {

    @Builder.Default
    private Integer page = 0;

    @Builder.Default
    private Integer size = 10;

    @Builder.Default
    private String sortField = "id";

    @Builder.Default
    private Sort.Direction direction = Sort.Direction.DESC;

    @Builder.Default
    private Boolean fetchAll = false;
}