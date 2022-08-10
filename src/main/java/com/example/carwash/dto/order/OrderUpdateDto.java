package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderUpdateDto {
    private Long offerId;
    private LocalDateTime dateTime;
}
