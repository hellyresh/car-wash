package com.example.carwash.dto.order;

import com.example.carwash.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderUpdateDto {
    private Long userId;
    private Long offerId;
    private OrderStatus status;
    private LocalDate date;
    private LocalTime startTime;
    private Integer discount;
    @Positive
    private BigDecimal price;
    private Long boxId;
}
