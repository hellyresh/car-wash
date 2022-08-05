package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long offerId;
    @NotNull
    private Instant dateTime;
    @Positive
    private int discount;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private Long boxId;
}
