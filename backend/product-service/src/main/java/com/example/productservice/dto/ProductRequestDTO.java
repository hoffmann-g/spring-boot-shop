package com.example.productservice.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ProductRequestDTO(String name, String description, BigDecimal price) {
    
}
