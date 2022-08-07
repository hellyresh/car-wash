package com.example.carwash.dto.box;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalTime;

@Getter
@Setter
public class BoxUpdateDto {
    private LocalTime openTime;
    private LocalTime closeTime;
    @Positive
    private Double timeCoefficient;
}
