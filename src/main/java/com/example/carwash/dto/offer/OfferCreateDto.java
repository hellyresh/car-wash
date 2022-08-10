package com.example.carwash.dto.offer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferCreateDto {
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private Integer duration;
    @NotNull
    @Positive
    private BigDecimal price;
}
