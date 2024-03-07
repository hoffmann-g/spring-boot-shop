package com.example.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderservice.dto.OrderLineItemsRequestDTO;
import com.example.orderservice.dto.OrderLineItemsResponseDTO;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItems;
import com.example.orderservice.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRespository;
    
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO){
        Order order = new Order();

        List<OrderLineItems> orderLineItemsList = orderRequestDTO.orderLineItemsRequestDTOList()
            .stream() //cant instantiate a stream because 
            .map(obj -> mapToOrderLineItems(obj))
            .toList();

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItemsList);
        
        orderRespository.save(order);

        List<OrderLineItemsResponseDTO> responseList = order.getOrderLineItemsList()
            .stream()
            .map(obj -> mapToOrderLineResponse(obj))
            .toList();

        return OrderResponseDTO.builder().orderLineItemsResponseDTOList(responseList).build();

    }

    private OrderLineItemsResponseDTO mapToOrderLineResponse(OrderLineItems orderLineItems) {
        return OrderLineItemsResponseDTO.builder()
            .id(orderLineItems.getId())
            .price(orderLineItems.getPrice())
            .quantity(orderLineItems.getQuantity())
            .build();

    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsRequestDTO orderLineItemsRequestDTO){
        return OrderLineItems.builder()
            .skuCode(orderLineItemsRequestDTO.skuCode())
            .quantity(orderLineItemsRequestDTO.quantity())
            .price(orderLineItemsRequestDTO.price())
            .build();
    }

}
