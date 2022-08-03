package com.example.carwash.dto;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class BoxCreateDto {
    private LocalTime openTime;
    private LocalTime closeTime;
    private double timeCoefficient;
}
