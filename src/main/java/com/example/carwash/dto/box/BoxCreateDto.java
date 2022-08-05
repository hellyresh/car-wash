package com.example.carwash.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalTime;

@Getter
@Setter
public class BoxCreateDto {
    @NotNull
    private LocalTime openTime;
    @NotNull
    private LocalTime closeTime;
    @NotNull
    @Positive
    private double timeCoefficient;
}
