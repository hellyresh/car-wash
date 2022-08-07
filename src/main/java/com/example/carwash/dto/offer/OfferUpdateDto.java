package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
public class OfferUpdateDto {
    private String name;
    @Positive
    private Duration duration;
    @Positive
    private BigDecimal price;
}
