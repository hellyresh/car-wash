package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
public class OfferUpdateDto {
    @NotNull
    private Long id;
    private String name;
    @Positive
    private Duration duration;
    @Positive
    private BigDecimal price;
}
