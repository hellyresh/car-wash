package com.example.carwash.controller;

import com.example.carwash.dto.OrderDto;
import com.example.carwash.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("search")
    public List<OrderDto> filterOrders(@RequestParam Long boxId, @RequestParam LocalDateTime dateTime) {
        return orderService.showFilteredOrders(boxId, dateTime);
    }
}
