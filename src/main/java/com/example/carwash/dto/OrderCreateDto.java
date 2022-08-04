package com.example.carwash.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderCreateDto {
    private Long userId;
    private Long offerId;
    private Instant dateTime;
    private int discount;
    private BigDecimal price;
    private Long boxId;
}
