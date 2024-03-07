package com.example.orderservice.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record OrderLineItemsResponseDTO(Long id, BigDecimal price, Integer quantity) {
    
}
