package com.example.diplom;

import com.example.diplom.dto.OrderDTO;
import com.example.diplom.entity.Currency;
import com.example.diplom.entity.Order;
import com.example.diplom.entity.Product;
import com.example.diplom.exception.EntityNotFountException;
import com.example.diplom.mapper.OrderMapper;
import com.example.diplom.repository.OrderRepository;
import com.example.diplom.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    public void setup() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
        Product p = new Product();
        p.setCostPrice(BigDecimal.valueOf(333));
        order = Order.builder()
                .product(p)
                .currency(new Currency())
                .weight(122)
                .price(BigDecimal.valueOf(3333))
                .build();
    }

    @Test
    @DisplayName("Testing saving Order success")
    void testSaveOrderSuccess() {

        given(orderRepository.save(order)).willReturn(order);
        // when -  action or the behaviour that we are going test
        Order savedOrder = orderService.createOrder(OrderMapper.orderToOrderDTO(order));
        // then - verify the output
        assertThat(savedOrder).isNotNull();
    }

    @DisplayName("JUnit test for saveOrder method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveOrder_thenThrowsException() {
        // given - precondition or setup
        given(orderRepository.findById(order.getId()))
                .willReturn(Optional.of(order));
        // when -  action or the behaviour that we are going test
        Assertions.assertThrows(EntityNotFountException.class, () -> {
            orderService.createOrder(OrderMapper.orderToOrderDTO(order));
        });
        // then
        verify(orderRepository, never()).save(any(Order.class));
    }

    @DisplayName("JUnit test for getAllOrders method")
    @Test
    public void givenOrdersList_whenGetAllOrders_thenReturnOrdersList() {
        // given - precondition or setup
        Product p = new Product();
        p.setCostPrice(BigDecimal.valueOf(133));
        Order order1 = Order.builder()
                .id(2L)
                .product(p)
                .currency(new Currency())
                .weight(152)
                .username("sss")
                .price(BigDecimal.valueOf(33333))
                .build();

        given(orderRepository.findAll()).willReturn(List.of(order, order1));
        // when -  action or the behaviour that we are going test
        List<OrderDTO> orderList = orderService.getOrders();
        // then - verify the output
        assertThat(orderList).isNotNull();
        assertThat(orderList.size()).isEqualTo(2);
    }

    // JUnit test for getAllOrders method
    @DisplayName("JUnit test for getAllOrders method (negative scenario)")
    @Test
    public void givenEmptyOrdersList_whenGetAllOrders_thenReturnEmptyOrdersList() {
        Product p = new Product();
        p.setCostPrice(BigDecimal.valueOf(333));
        Order order1 = Order.builder()
                .id(2L)
                .product(p)
                .currency(new Currency())
                .weight(111)
                .username("sss")
                .price(BigDecimal.valueOf(2222))
                .build();

        given(orderRepository.findAll()).willReturn(Collections.emptyList());
        // when -  action or the behaviour that we are going test
        List<OrderDTO> orderList = orderService.getOrders();
        // then - verify the output
        assertThat(orderList).isEmpty();
        assertThat(orderList.size()).isEqualTo(0);
    }

    // JUnit test for updateOrder method
    @DisplayName("JUnit test for updateOrder method")
    @Test
    public void givenOrderObject_whenUpdateOrder_thenReturnUpdatedOrder() {
        // given - precondition or setup
        Long id = 3L;
        given(orderRepository.save(order)).willReturn(order);
        order.setId(id);
        order.setWeight(1111);
        // when -  action or the behaviour that we are going test
        Order updatedOrder = orderService.updateOrder(id, OrderMapper.orderToOrderDTO(order));
        // then - verify the output
        assertThat(updatedOrder.getWeight()).isEqualTo(1111);
    }

    // JUnit test for deleteOrder method
    @DisplayName("JUnit test for deleteOrder method")
    @Test
    public void givenOrderId_whenDeleteOrder_thenNothing() {
        // given - precondition or setup
        long orderId = 1L;

        willDoNothing().given(orderRepository).deleteById(orderId);
        // when -  action or the behaviour that we are going test
        orderService.deleteOrder(orderId);
        // then - verify the output
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    // JUnit test for getOrderById method
    @DisplayName("JUnit test for getOrderById method")
    @Test
    public void givenOrderId_whenGetOrderById_thenReturnOrderObject() {
        Product p = new Product();
        p.setCostPrice(BigDecimal.valueOf(333));
        Order order1 = Order.builder()
                .id(2L)
                .product(p)
                .currency(new Currency())
                .weight(111)
                .username("sss")
                .price(BigDecimal.valueOf(2222))
                .build();
        Long orderId = 2L;
        // given
        given(orderRepository.findById(orderId)).willReturn(Optional.of(order1));
        // when
        OrderDTO savedOrder = orderService.getOrderById(orderId);
        // then
        assertThat(savedOrder).isNotNull();
    }
}
