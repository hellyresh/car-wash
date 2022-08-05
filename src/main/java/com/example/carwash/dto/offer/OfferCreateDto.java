package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
public class OfferCreateDto {
    @NotBlank
    private String name;
    @NotNull
    private Duration duration;
    @NotNull
    @Positive
    private BigDecimal price;
}
