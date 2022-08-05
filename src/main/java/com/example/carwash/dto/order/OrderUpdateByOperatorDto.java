package com.example.carwash.dto.order;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderUpdateByOperatorDto {
    @NotNull
    private Long id;
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private Instant dateTime;
    private int discount;
    private BigDecimal price;
}
