package com.example.diplom.service;

import com.example.diplom.dto.OrderDTO;
import com.example.diplom.entity.Order;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.OrderMapper;
import com.example.diplom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getOrders() {
        return orderRepository.findAll()
                .stream().map(OrderMapper::orderToOrderDTO).collect(Collectors.toList());
    }

    public Order createOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .price(orderDTO.getPrice())
                .product(orderDTO.getProduct())
                .currency(orderDTO.getCurrency())
                .weight(orderDTO.getWeight())
                .build();

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, OrderDTO orderDTO) {
        return orderRepository.findById(id)
                .map(order -> {
                    OrderMapper.orderDtoToOrder(orderDTO);
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    orderDTO.setId(id);
                    return orderRepository.save(OrderMapper.orderDtoToOrder(orderDTO));
                });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Long id){
//        Order order;
//        Optional<Order> orderOptional = orderRepository.findById(id);
//
//        if (orderOptional.isPresent()) {
//            order = orderOptional.get();
//        } else {
//            throw new EntityNotFountException("Order with id: " + id + " was not found");
//        }
//
//        return order;
        return orderRepository.findById(id).orElse(null);

    }
}
