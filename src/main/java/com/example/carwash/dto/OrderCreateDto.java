package com.example.carwash.dto;

import com.example.carwash.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderCreateDto {
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private Instant dateTime;
    private int discount;
    private BigDecimal price;
}
