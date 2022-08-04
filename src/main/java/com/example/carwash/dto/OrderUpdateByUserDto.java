package com.example.carwash.dto;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderUpdateByUserDto {
    private OrderStatus status;
    private Instant dateTime;
    private Long boxId;
}
