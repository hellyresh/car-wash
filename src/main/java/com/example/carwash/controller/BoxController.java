package com.example.carwash.controller;

import com.example.carwash.dto.box.BoxCreateDto;
import com.example.carwash.dto.box.BoxDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.order.OrderUpdateByOperatorDto;
import com.example.carwash.service.BoxService;
import com.example.carwash.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boxes")
public class BoxController {

    private final BoxService boxService;
    private final OrderService orderService;

    //TODO admin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoxDto create(@Valid @RequestBody BoxCreateDto boxCreateDto) {
        return boxService.create(boxCreateDto);
    }

    //todo operator + current operator (box)
    @GetMapping("orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getBoxOrders() {
        return orderService.getBoxOrders();
    }

    //todo operator + current operator
//    @PutMapping("orders/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public OrderDto updateOrder(@PathVariable Long id,
//                                @Valid @RequestBody OrderUpdateByOperatorDto orderUpdateDto) {
//        return orderService.update(id, orderUpdateDto);
//    }

    //todo admin
    @GetMapping("{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getOrdersByBoxId(@PathVariable String id) {
        return orderService.getOrdersByBoxId(id);
    }


}
