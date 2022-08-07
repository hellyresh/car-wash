package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long offerId;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
}
