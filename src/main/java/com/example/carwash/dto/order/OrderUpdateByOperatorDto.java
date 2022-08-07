package com.example.carwash.dto.order;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderUpdateByOperatorDto {
    private Long offerId;
    private OrderStatus status;
    private LocalDate date;
    private LocalTime startTime;
    private Integer discount;
    private BigDecimal price;
}
