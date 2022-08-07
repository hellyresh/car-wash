package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class OrderUpdateByUserDto {
    private LocalDate date;
    private LocalTime startTime;
    private Long offerId;
}
