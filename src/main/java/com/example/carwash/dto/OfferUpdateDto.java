package com.example.carwash.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
public class OfferUpdateDto {
    private Long id;
    private String name;
    private Duration duration;
    private BigDecimal price;
}
