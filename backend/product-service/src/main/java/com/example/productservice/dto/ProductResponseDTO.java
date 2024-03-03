package com.example.productservice.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ProductResponseDTO(String id, String name, String description, BigDecimal price) {
    
}
