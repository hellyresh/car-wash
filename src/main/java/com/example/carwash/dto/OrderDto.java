package com.example.carwash.dto;

import com.example.carwash.model.Order;
import com.example.carwash.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderDto {
    private Long id;
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private Instant dateTime;
    private int discount;
    private BigDecimal price;

    public static OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.id = order.getId();
        orderDto.userId = order.getUser().getId();
        orderDto.offerId = order.getOffer().getId();
        orderDto.status = order.getStatus();
        orderDto.dateTime = order.getDateTime();
        orderDto.discount = order.getDiscount();
        orderDto.price = order.getPrice();
        return orderDto;
    }
}
