package com.example.carwash.controller;

import com.example.carwash.dto.order.OrderCreateDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.model.User;
import com.example.carwash.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //TODO admin&operator
    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> filterOrders(@RequestParam Long boxId, @RequestParam LocalDateTime dateTime) {
        return orderService.showFilteredOrders(boxId, dateTime);
    }

    //TODO admin&operator
    @PutMapping("{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto cancelOrder(@PathVariable Long id) {
        return orderService.cancel(id);
    }

    //TODO user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto, new User());
    }

    //TODO user
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getUserOrders() {
        //TODO current user
        User user = new User();
        return orderService.getUserOrders(user);
    }

    //TODO user + current user
//    @PutMapping("{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public OrderDto updateOrder(@PathVariable Long id, @Valid @RequestBody OrderUpdateByUserDto orderUpdateDto) {
//        return orderService.update(id, orderUpdateDto);
//    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String checkIn(@PathVariable Long id) {
        return orderService.checkIn(id);
    }




}
