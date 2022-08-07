package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderUpdateByUserDto {
    private LocalDateTime dateTime;
    private Long offerId;
}
