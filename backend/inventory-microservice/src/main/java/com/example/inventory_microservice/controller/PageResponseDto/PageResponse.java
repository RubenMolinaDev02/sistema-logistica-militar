package com.example.inventory_microservice.controller.PageResponseDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse<T> {

    private List<T> content;

    private long totalElements;

    private int totalPages;

    private int page;

    private int size;

    private boolean last;
}