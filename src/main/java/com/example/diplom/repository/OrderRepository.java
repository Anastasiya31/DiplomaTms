package com.example.diplom.repository;

import com.example.diplom.entity.Invoice;
import com.example.diplom.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
