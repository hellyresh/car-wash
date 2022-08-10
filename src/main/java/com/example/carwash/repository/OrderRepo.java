package com.example.carwash.repository;

import com.example.carwash.model.Order;
import com.example.carwash.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findClosest(LocalDateTime now);

    List<Order> findAllByStatusAndDateTime(OrderStatus submitted, LocalDateTime now);
}
