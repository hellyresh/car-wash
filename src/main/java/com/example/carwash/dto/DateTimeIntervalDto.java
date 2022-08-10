package com.example.carwash.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class DateTimeIntervalDto {
    @NotNull
    private LocalDateTime startDateTime;
    @NotNull
    private LocalDateTime endDateTime;
}
