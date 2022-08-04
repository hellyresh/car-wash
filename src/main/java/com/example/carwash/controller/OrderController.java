package com.example.carwash.controller;

import com.example.carwash.dto.OrderCreateDto;
import com.example.carwash.dto.OrderDto;
import com.example.carwash.dto.OrderUpdateByUserDto;
import com.example.carwash.model.User;
import com.example.carwash.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //TODO admin&operator
    @GetMapping("search")
    public List<OrderDto> filterOrders(@RequestParam Long boxId, @RequestParam LocalDateTime dateTime) {
        return orderService.showFilteredOrders(boxId, dateTime);
    }

    //TODO admin&operator
    @PutMapping("{id}/cancel")
    public OrderDto cancelOrder(@PathVariable Long id){
        return orderService.cancelOrder(id);
    }

    //TODO user
    @PostMapping
    public OrderDto create(@RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto);
    }

    //TODO user
    @GetMapping
    public List<OrderDto> getUserOrders() {
        //TODO current user
        User user = new User();
        return orderService.getUserOrders(user);
    }

    //TODO user
    @PutMapping("{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderUpdateByUserDto orderUpdateDto){
        return orderService.updateOrder(id, orderUpdateDto);
    }


}
