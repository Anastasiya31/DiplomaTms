package com.example.diplom.service;

import com.example.diplom.dto.OrderDTO;
import com.example.diplom.entity.Order;
import com.example.diplom.mapper.OrderMapper;
import com.example.diplom.repository.OrderRepository;
import com.example.diplom.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }
    public List<OrderDTO> getOrdersUser() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        return orderRepository.findOrdersByUsername(user.getUsername())
                .stream()
                .map(OrderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }
    @SneakyThrows
    public Order createOrder(OrderDTO orderDTO) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDTO.setUsername(user.getUsername());
        return orderRepository.save(OrderMapper.orderDtoToOrder(orderDTO));
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

    public OrderDTO getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with id: " + id + " was not found"));
        return OrderMapper.orderToOrderDTO(order);
    }
}
