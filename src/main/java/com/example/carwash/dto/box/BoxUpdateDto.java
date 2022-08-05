package com.example.carwash.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalTime;

@Getter
@Setter
public class BoxUpdateDto {
    @NotNull
    private Long id;
    private LocalTime openTime;
    private LocalTime closeTime;
    @Positive
    private double timeCoefficient;
}
