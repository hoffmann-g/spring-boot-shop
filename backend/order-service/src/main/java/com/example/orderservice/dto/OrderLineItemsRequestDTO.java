package com.example.orderservice.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record OrderLineItemsRequestDTO(Long id, String skuCode, BigDecimal price, Integer quantity) {

}
