package com.example.carwash.dto.order;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderUpdateByOperatorDto {
    private Long offerId;
    private OrderStatus status;
    private LocalDateTime dateTime;
    @Positive
    private Duration duration;
    @Positive
    private Integer discount;
}
