package com.example.orderservice.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record OrderResponseDTO(List<OrderLineItemsResponseDTO> orderLineItemsResponseDTOList) {

}
