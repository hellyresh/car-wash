package com.example.carwash.controller;

import com.example.carwash.dto.order.OrderCreateDto;
import com.example.carwash.dto.order.OrderDto;
import com.example.carwash.dto.order.OrderUpdateDto;
import com.example.carwash.dto.orderBill.OrderBillDto;
import com.example.carwash.service.OrderBillService;
import com.example.carwash.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderBillService orderBillService;

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @GetMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> filterOrders(@RequestParam Long boxId, @RequestParam LocalDateTime dateTime) {
        return orderService.showFilteredOrders(boxId, dateTime);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @PutMapping("{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto cancelOrder(@PathVariable Long id) {
        return orderService.cancel(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto);
    }

    @PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN', 'USER')")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@Valid @RequestBody OrderUpdateDto orderUpdateDto, @PathVariable Long id) {
        return orderService.update(id, orderUpdateDto);
    }

    @PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
    @PutMapping("{id}/finish")
    @ResponseStatus(HttpStatus.OK)
    public OrderBillDto finishOrder(@PathVariable Long id) {
        return orderService.finish(id);
    }

    @PreAuthorize("hasAnyRole('OPERATOR', 'ADMIN')")
    @PutMapping("{id}/set-discount")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto setOrderDiscount(@PathVariable Long id, @RequestParam Integer discount) {
        return orderService.setDiscount(id, discount);
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}/checkin")
    @ResponseStatus(HttpStatus.OK)
    public void checkIn(@PathVariable Long id) {
        orderService.checkIn(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("bills")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderBillDto> getUserBills() {
        return orderBillService.getBills();
    }


}
