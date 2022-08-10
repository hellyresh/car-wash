package com.example.carwash.dto.operator;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class DiscountDto {
    @NotNull
    @Min(0)
    Integer minDiscount;
    @NotNull
    @Max(50)
    Integer maxDiscount;
}
