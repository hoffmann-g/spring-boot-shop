package com.example.orderservice.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record OrderRequestDTO(List<OrderLineItemsRequestDTO> orderLineItemsRequestDTOList) {
    
}
