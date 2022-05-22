package com.example.diplom.mapper;

import com.example.diplom.dto.OrderDTO;
import com.example.diplom.entity.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {
    public Order orderDtoToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .weight(orderDTO.getWeight())
                .currency(orderDTO.getCurrency())
                .product(orderDTO.getProduct())
                .price(orderDTO.getPrice())
                .build();
    }

    public OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .weight(order.getWeight())
                .currency(order.getCurrency())
                .product(order.getProduct())
                .price(order.getPrice())
                .build();
    }

}