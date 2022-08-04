package com.example.carwash.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class BoxUpdateDto {
    private Long id;
    private LocalTime openTime;
    private LocalTime closeTime;
    private double timeCoefficient;
}
