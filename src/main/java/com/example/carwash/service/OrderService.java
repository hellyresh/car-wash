package com.example.carwash.service;

import com.example.carwash.dto.OrderDto;
import com.example.carwash.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    //TODO
    public List<OrderDto> showFilteredOrders(Long boxId, LocalDateTime dateTime) {
        return orderRepo.findAll().stream().map(OrderDto::toDto).toList();
    }
}
