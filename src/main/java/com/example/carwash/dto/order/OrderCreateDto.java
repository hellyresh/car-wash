package com.example.carwash.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private Long offerId;
    @NotNull
    private LocalDateTime dateTime;
}
