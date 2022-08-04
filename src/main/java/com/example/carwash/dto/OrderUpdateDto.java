package com.example.carwash.dto;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderUpdateDto {
    private Long id;
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private Instant dateTime;
    private int discount;
    private BigDecimal price;
    private Long boxId;
}
