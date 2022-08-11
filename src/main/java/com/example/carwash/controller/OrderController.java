package com.example.carwash.controller;

import com.example.carwash.dto.DateTimeIntervalDto;
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
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderBillService orderBillService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return orderService.getOrders();
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
    @PutMapping("{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto cancelOrder(@PathVariable Long id) {
        return orderService.cancel(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        return orderService.create(orderCreateDto);
    }

    @PreAuthorize("hasAnyAuthority('OPERATOR', 'ADMIN', 'USER')")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@Valid @RequestBody OrderUpdateDto orderUpdateDto, @PathVariable Long id) {
        return orderService.update(id, orderUpdateDto);
    }

    @PreAuthorize("hasAnyAuthority('OPERATOR', 'ADMIN')")
    @PutMapping("{id}/finish")
    @ResponseStatus(HttpStatus.OK)
    public OrderBillDto finishOrder(@PathVariable Long id) {
        return orderService.finish(id);
    }

    @PreAuthorize("hasAnyAuthority('OPERATOR', 'ADMIN')")
    @PutMapping("{id}/discount")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto setOrderDiscount(@PathVariable Long id, @RequestParam Integer discount) {
        return orderService.setDiscount(id, discount);
    }


    @PreAuthorize("hasAnyAuthority('USER', 'OPERATOR', 'ADMIN')")
    @PutMapping("{id}/checkin")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto checkIn(@PathVariable Long id) {
        return orderService.checkIn(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("bills")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderBillDto> getUserBills() {
        return orderBillService.getUserBills();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("revenue")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getRevenue(@Valid @RequestBody DateTimeIntervalDto dateTimeIntervalDto) {
        return orderBillService.getRevenue(dateTimeIntervalDto);
    }


}
