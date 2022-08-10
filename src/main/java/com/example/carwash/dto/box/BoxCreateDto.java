package com.example.carwash.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalTime;

@Getter
@Setter
public class BoxCreateDto {
    @NotNull(message = "lol")
    private LocalTime openTime;
    @NotNull(message = "kek")
    @NotNull
    private LocalTime closeTime;
    @NotNull(message = "kek")
    @Positive(message = "lol")
    private Double timeCoefficient;
}
